package com.tripco.t13.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import spark.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Trip{
    int version;
    String type;
    String title;
    ArrayList<Location> places;
    Options options;
    ArrayList<Long> distances;
    //TODO: ADD MAP
    String map;
    /*
        Common units and radii for reference:
        miles: 3959
        kilometers:6371
        nautical miles: 3440
        bananas: 35828571
     */

    Trip(){} //allow option of none for initialization.

    //A constructor for short path algorithm in shortOptimization() method in TripCalculate.java to deep copy a Trip object.
    Trip(Trip trip){
        this.version = trip.version;
        this.type = trip.type;
        this.title = trip.title;
        this.places = trip.places;
        this.options = trip.options;
        this.distances = trip.distances;
        this.map = trip.map;
    }

    //fills distances arraylist with distance between each Location in places arraylist and fills final space in
    //distances arraylist with round trip distance. Distances are calculated using getDistanceNum method from Distance class.
    public ArrayList<Long> getTripDistances(){
        distances = new ArrayList<Long>();
        for(int i = 0; i < places.size() - 1; i++) {
            distances.add(Distance.getDistanceNum(places.get(i).latitude, places.get(i).longitude, places.get(i+1).latitude, places.get(i+1).longitude, options.getRadius()));
        }
        //distances.add(Distance.getDistanceNum(places.get(places.size()-1).latitude, places.get(places.size()-1).longitude, places.get(0).latitude, places.get(0).longitude, options.getRadius()));

        return distances;
    }

    //Returns hardcoded map
    public String svg(){
        return "<svg width=\"1920\" height=\"960\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\"svg_4\"> <svg id=\"svg_1\" height=\"960\" width=\"1920\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_2\"> <title>Layer 1</title> <rect fill=\"rgb(119, 204, 119)\" stroke=\"black\" x=\"0\" y=\"0\" width=\"1920\" height=\"960\" id=\"svg_3\"/> </g> </svg> </g> <g id=\"svg_9\"> <svg id=\"svg_5\" height=\"480\" width=\"960\" y=\"240\" x=\"480\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_6\"> <title>Layer 2</title> <polygon points=\"0,0 960,0 960,480 0,480\" stroke-width=\"12\" stroke=\"brown\" fill=\"none\" id=\"svg_8\"/> <polyline points=\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\" fill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"svg_7\"/> </g> </svg> </g> </g> </svg>";
    }

}
