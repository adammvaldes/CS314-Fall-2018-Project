package com.tripco.t13.server;

import com.tripco.t13.planner.Place;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ShortOptimization {

    private String units;
    private ArrayList<Location> places;

    ShortOptimization(String units, ArrayList<Location> places) {
        this.units = units;
        this.places = places;
    }

    ArrayList<Location> travelingSalesman(ArrayList<Location> places, Location origin, String units) {
        boolean[] visitedPlaces = new boolean[places.size()];
        boolean allPlacesVisited = false;
        ArrayList<Location> sortedPlaces = new ArrayList<>();

        sortedPlaces.add(origin);
        visitedPlaces[places.indexOf(origin)] = true;
        while (!allPlacesVisited) {
            Location currentPlace = calculateDistances(origin, places, units, visitedPlaces);
            visitedPlaces[places.indexOf(currentPlace)] = true;
            sortedPlaces.add(currentPlace);

            for (boolean visitedPlace : visitedPlaces) {
                if (!visitedPlace) {
                    allPlacesVisited = false;
                    break;
                }

                allPlacesVisited = true;
            }
        }

        sortedPlaces.add(origin); //add origin to complete the trip
        return sortedPlaces;
    }

    public Location calculateDistances(Location origin, ArrayList<Location> places, String units, boolean[] visitedPlaces) {
        Distance distance = new Distance();

        int shortestDistance = Integer.MAX_VALUE;
        Location closestPlace = null;

        for (int place = 0; place < places.size(); place++) {
            if (!places.get(place).equals(origin) && !visitedPlaces[place]) {
                if (Distance.getDistanceNum(origin.latitude, origin.longitude, places.get(place).latitude,
                        places.get(place).longitude, distance.getRadius(units)) <= shortestDistance) {
                    shortestDistance = Distance.getDistanceNum(origin.latitude, origin.longitude,
                    places.get(place).latitude,places.get(place).longitude, distance.getRadius(units));
                    closestPlace = places.get(place);
                }
            }
        }

        return closestPlace;
    }
}