package model.matter;

/*
 *      In this simulation, the Liquid acts as a environment where Solid Matter
 *      interacts in a slightly different manner, undergoing physical effects
 *      such as being slowed down by drag attributed to the viscosity of the Liquid.
 *
 *      There can only be a single Liquid present in the Sandbox at a given time.
 */


import model.Sandbox;

import java.awt.*;

public abstract class Liquid extends Matter {

    // DEFAULT PARAMETERS
    private static final double defaultViscosity = 0.0091;  // the viscosity of water
    private static final double defaultDensity = 1000;
    private static final double defaultLevel = Sandbox.HEIGHT * Sandbox.pixelsToMeters / 2;
    private static final String defaultName = "A Suspicious Liquid";

    private double viscosity;
    private double density;

    private double level;              // height of liquid on screen
    private String name;

    /*
     * CONSTRUCTORS
     */


    // EFFECTS: creates Liquid object with default parameters
    public Liquid() {
        super();
        viscosity = defaultViscosity;
        density = defaultDensity;
        level = defaultLevel;
        volume = getVolume();
        mass = getMass();
        name = defaultName;
    }

    // EFFECTS: creates Liquid object with user-specified parameters
    public Liquid(Color c, double v, double d, double l, String n) {
        super(c);
        viscosity = v;
        density = d;
        level = l;
        volume = getVolume();
        mass = getMass();
        name = n;
    }

    /*
     * GETTERS
     */


    // EFFECTS: returns the viscosity of the Liquid
    public double getViscosity() {
        return viscosity;
    }

    // EFFECTS: returns the Liquid level, in m above the base of the Sandbox
    public double getLevel() {
        return level;
    }

    // EFFECTS: returns the volume of the liquid in m^3
    public double getVolume() {
        return Sandbox.WIDTH * level * depth;
    }

    // REQUIRES: volume and density are already set
    // EFFECTS: returns the mass of the liquid in kg
    public double getMass() {
        return density * volume;
    }

    // EFFECTS: returns the density of the Liquid
    public double getDensity() {
        return density;
    }

    // EFFECTS: returns the name of the liquid
    public String getName() {
        return name;
    }


    /*
     * SETTERS
     */

    // REQUIRES: viscosity must be in range [0, 1]
    // MODIFIES: this
    // EFFECTS: sets viscosity of Liquid to specified amount
    public void setViscosity(double v) {
        viscosity = v;
    }

    // REQUIRES: input level is (m) within boundaries of screen (Y in [0, HEIGHT*pixelsPerMeter])
    // MODIFIES: this
    // EFFECTS: sets the level of the liquid to specified amount, in m
    public void setLevel(double l) {
        level = l;
        volume = getVolume();
        mass = getMass();
    }

    public void resetLevel() {}

    public void changeLevel(double dl) {}

}


