package model.matter;


import model.Sandbox;

import java.awt.*;

public abstract class Solid extends Matter {

    // DEFAULT PARAMETERS
    public static final double defaultMass = 5.0;
    private static final String solidState = "Solid";

    // PHYSICAL PROPERTIES

    protected double fractionSubmerged = 0.0;

    // UI PROPERTIES, all in m
    protected double posX;
    protected double posY;

    protected double speedX = 0;
    protected double speedY = 0;

    protected String shape;
    protected static int nextIndex = 1;
    protected int index;


    // EFFECTS: creates new SandboxObject with default properties
    public Solid() {
        mass = defaultMass;
        posX = Sandbox.WIDTH / 2;
        posY = Sandbox.HEIGHT / 2;
        speedX = 0;
        speedY = 0;
        color = defaultColor;
        state = solidState;
        index = nextIndex++;
    }


    // EFFECTS: creates new Solid with user-specified properties
    public Solid(double m, Color c, double x, double y) {
        mass = m;
        posX = x;
        posY = y;
        speedX = 0;
        speedY = 0;
        color = c;
        state = solidState;
        index = nextIndex++;
        volume = getVolume();
    }


    /*
     * G E T T E R S
     */

    // EFFECTS: returns mass of Solid, in kg
    public double getMass() {
        return mass;
    }

    // EFFECTS: returns volume of Solid, in m^3
    public abstract double getVolume();

    // EFFECTS: returns the density of Solid, in kg/m^3
    public double getDensity() {
        return mass / volume;
    }

    // EFFECTS: returns true if the Solid is submerged
    public boolean isSubmerged() {
        return (fractionSubmerged > 0.0);
    }

    // EFFECTS: returns the fraction of the solid submerged
    // !!!
    public double getFractionSubmerged() {
        return fractionSubmerged;
    }


    // EFFECTS: returns the shape of the Solid
    public String getShape() {
        return shape;
    }

    // EFFECTS: returns the x-coordinate of the Solid
    public double getPosX() {
        return posX;
    }

    // EFFECTS: returns the x-coordinate of the Solid
    public double getPosY() {
        return posY;
    }

    // EFFECTS: returns the x-speed of the Solid
    public double getSpeedX() {
        return speedX;
    }

    // EFFECTS: returns the y-speed of the Solid
    public double getSpeedY() {
        return speedY;
    }


    // EFFECTS: returns the index of the solid
    public int getIndex() {
        return index;
    }



    // METHODS

    // MODIFIES: this
    // EFFECTS: moves the SandboxObject along the screen, unless it goes off the screen.
    //          returns true if object successfully moved, false otherwise
    public boolean moveObject() {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: increases/decreases speedX and speedY
    public void addSpeed(double x, double y) {
        speedX += x;
        speedY += y;
    }

    // REQUIRES: input must be in range [0, 1]
    // MODIFIES: this
    // EFFECTS: sets fraction of the Solid that is submerged in a Liquid
    public void setFractionSubmerged(double f) {
        if (0 <= f && f <= 1) {
            fractionSubmerged = f;
        }
    }


}
