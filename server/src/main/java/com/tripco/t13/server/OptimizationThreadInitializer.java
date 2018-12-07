package com.tripco.t13.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;

import java.util.*;
import java.util.concurrent.*;

public class OptimizationThreadInitializer {
    Trip trip = null;
    boolean isCorrectFormat;
    Long[][] distanceLibrary;

    OptimizationThreadInitializer(Request request) {
        JsonParser jsonParser = new JsonParser();
        JsonElement requestBody = jsonParser.parse(request.body());
        //Converting to a Java class
        Gson gson = new Gson();
        try {
            trip = gson.fromJson(requestBody, Trip.class);
            isCorrectFormat = validateTripRequestFormat(trip);
            threadCreator();
        } catch (Exception e) {
            isCorrectFormat = false;
        }
    }

    //Constructor for testing.
    OptimizationThreadInitializer(Trip trip) {
        this.trip = trip;
    }

    public void threadCreator() {
        String optimization = trip.options.optimization;
        double radius = trip.options.getRadius();
        if (optimization != null && (optimization.equals("short") || optimization.equals("shorter"))) {
            long lStartTime0 = System.nanoTime();
            calculateDistanceTable(radius);
            System.out.println("Elapsed time for distance library in milliseconds: " + (System.nanoTime() - lStartTime0) / 1000000);

            int shortestCumulativeDistance = 0;
            for (int i = 0; i < trip.places.size(); i++) {
                shortestCumulativeDistance += distanceLibrary[i][i + 1];
            }
            shortestCumulativeDistance += distanceLibrary[trip.places.size()][0]; //Round Trip

            ArrayList<Location> retainOriginalPlaces = new ArrayList<>(trip.places.size());
            retainOriginalPlaces.addAll(trip.places);

            Set<Callable<int[]>> threads = new HashSet<>();
            int cores = Runtime.getRuntime().availableProcessors();
            int start = 0;
            for (int i = 0; i < cores; i++) {
                int end = (start + trip.places.size() / cores);
                if (i == cores - 1) {
                    end += trip.places.size() % cores;
                }
                //Set of starting locations that each thread will look at.
                int[] chunk = {start, end - 1};
                start = end;
                threads.add(new TripCalculate(chunk, trip, shortestCumulativeDistance, distanceLibrary));
            }

            ExecutorService executorService = Executors.newFixedThreadPool(cores);
            try {
                long lStartTime1 = System.nanoTime();
                List<Future<int[]>> results = executorService.invokeAll(threads);
                System.out.println("Elapsed time for invokeAll in milliseconds: " + (System.nanoTime() - lStartTime1) / 1000000);
                executorService.shutdown();

                int shortestDistance = Integer.MAX_VALUE;
                int[] shortestTripPointerArray = new int[trip.places.size() + 1];

                for (Future<int[]> places : results) {
                    int tempShortestDistance = places.get()[places.get().length - 1];
                    if (tempShortestDistance < shortestDistance) {
                        shortestDistance = tempShortestDistance;
                        shortestTripPointerArray = places.get();
                    }
                }

                for (int i = 0; i < trip.places.size(); i++) {
                    trip.places.set(i, retainOriginalPlaces.get(shortestTripPointerArray[i]));
                }

                trip.places.add(trip.places.get(0)); //Make it a round trip.
                trip.getTripDistances();
                trip.places.remove(trip.places.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //if(trip.places.get(0) != trip.places.get(trip.places.size()-1))
            trip.places.add(trip.places.get(0)); //Make it a round trip.
            trip.getTripDistances();
            trip.places.remove(trip.places.size() - 1);
        }
    }

    public void calculateDistanceTable(double radius) {
        distanceLibrary = new Long[trip.places.size() + 1][trip.places.size() + 1];

        Set<Callable<Long[][]>> threads = new HashSet<>();
        int cores = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < cores; i++) {
            threads.add(new ThreadedDistanceLibrary(cores, i, trip.places, radius));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        try {
            List<Future<Long[][]>> results = executorService.invokeAll(threads);
            executorService.shutdown();

            int counter = 0;
            for (Future<Long[][]> distLibChunk : results) {
                for (Long[] chunkRow : distLibChunk.get()) {
                    if (chunkRow[0] != null) {
                        distanceLibrary[counter] = chunkRow;
                    }
                    counter++;
                }
                counter = 0;
            }

            distanceLibrary[trip.places.size()] = distanceLibrary[0]; //Round trip calculation
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateTripRequestFormat(Trip trip) {
        //check if format of request if correct: type:"trip", version 1 or 2 or 3 or 4
        if (
                Objects.equals(trip.type, "trip") &&
                        (trip.version <= 4) &&
                        trip.places != null && trip.options != null) {
            return true;
        }
        return false;
    }

    public String getTripJson() {
        Gson gson = new Gson();
        if (isCorrectFormat) {
            return gson.toJson(trip);
        } else {
            return "{}"; //return {} if incorrect request format
        }
    }
}
