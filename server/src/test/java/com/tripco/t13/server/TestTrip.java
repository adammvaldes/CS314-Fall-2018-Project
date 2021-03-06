package com.tripco.t13.server;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

/*
  This class contains tests for the Trip class.
 */
@RunWith(JUnit4.class)
public class TestTrip {
    Trip trip;

    // Setup to be done before every test in TestTrip
    @Before
    public void initialize() {
        trip = new Trip();
        trip.options = new Options();
        trip.options.units = "miles";
        trip.title = "Shopping Loop";
        ArrayList<Location> initialLocations = new ArrayList<Location>();
        Location l1 = new Location();
        Location l2 = new Location();
        Location l3 = new Location();
        l1.id = "dnvr";
        l1.name = "Denver";
        l1.latitude = 39.7392;
        l1.longitude = -104.9903;
        l2.id = "bldr";
        l2.name = "Boulder";
        l2.latitude = 40.01499;
        l2.longitude = -105.27055;
        l3.id = "foco";
        l3.name = "Fort Collins";
        l3.latitude = 40.585258;
        l3.longitude = -105.084419;
        Collections.addAll(initialLocations, l1, l2, l3);
        trip.places = initialLocations;
    }

    @Test
    public void testTrue() {
        // assertTrue checks if a statement is true
        assertTrue(true == true);
    }

    @Test
    public void testOptionsMiles(){
        trip.options.units = "miles";
        trip.getTripDistances();
        assertEquals(3959, trip.options.getRadius(), 0.001);
        ArrayList<Long> expectedDistances = new ArrayList<Long>();
        Collections.addAll(expectedDistances, (long)24, (long)41);
        assertEquals(expectedDistances, trip.distances);
    }

    @Test
    public void testOptionsKilometers() {
        trip.options.units = "kilometers";
        trip.getTripDistances();
        assertEquals(6371, trip.options.getRadius(), 0.001);
        ArrayList<Long> expectedDistances = new ArrayList<Long>();
        Collections.addAll(expectedDistances, (long)39, (long)65);
        assertEquals(expectedDistances, trip.distances);
    }

    @Test
    public void testOptionsNauticalMiles() {
        trip.options.units = "nautical miles";
        trip.getTripDistances();
        assertEquals(3440, trip.options.getRadius(), 0.001);
        ArrayList<Long> expectedDistances = new ArrayList<Long>();
        Collections.addAll(expectedDistances, (long)21, (long)35);
        assertEquals(expectedDistances, trip.distances);
    }

    @Test
    public void testOptionsUserDefined1() {
        trip.options.units = "user defined";
        trip.options.unitName = "accurate miles";
        trip.options.unitRadius = 3958.7613;
        trip.getTripDistances();
        assertEquals(3958.7613, trip.options.getRadius(), 0.001);
        ArrayList<Long> expectedDistances = new ArrayList<Long>();
        Collections.addAll(expectedDistances, (long)24, (long)41);
        assertEquals(expectedDistances, trip.distances);
    }

    @Test
    public void testOptionsUserDefined2() {
        trip.options.units = "user defined";
        trip.options.unitName = "bananas";
        trip.options.unitRadius = 35828571.0;
        trip.getTripDistances();
        assertEquals(35828571.0, trip.options.getRadius(), 1);
        ArrayList<Long> expectedDistances = new ArrayList<Long>();
        Collections.addAll(expectedDistances, (long)218699, (long)367486);
        assertEquals(expectedDistances, trip.distances);
    }

    @Test
    public void testMap(){
        assertEquals("<svg width=\"1920\" height=\"960\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\"><!-- Created with SVG-edit - http://svg-edit.googlecode.com/ --> <g> <g id=\"svg_4\"> <svg id=\"svg_1\" height=\"960\" width=\"1920\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_2\"> <title>Layer 1</title> <rect fill=\"rgb(119, 204, 119)\" stroke=\"black\" x=\"0\" y=\"0\" width=\"1920\" height=\"960\" id=\"svg_3\"/> </g> </svg> </g> <g id=\"svg_9\"> <svg id=\"svg_5\" height=\"480\" width=\"960\" y=\"240\" x=\"480\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\"> <g id=\"svg_6\"> <title>Layer 2</title> <polygon points=\"0,0 960,0 960,480 0,480\" stroke-width=\"12\" stroke=\"brown\" fill=\"none\" id=\"svg_8\"/> <polyline points=\"0,0 960,480 480,0 0,480 960,0 480,480 0,0\" fill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"svg_7\"/> </g> </svg> </g> </g> </svg>", trip.svg());
    }
}
