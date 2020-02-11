package model.matter;

import java.awt.*;

/*
 * This class represents all of the objects that can be seen in the Sandbox.
 * It includes properties
 */

public abstract class Matter {

    // DEFAULT PARAMETERS
    public static final Color defaultColor = new Color(80, 88, 180);
    public static final double depth = 1.0;
    public static final double conserved = 0.95;

    // COMMON PARAMETERS
    protected String state;
    protected Color color;

    protected double volume;
    protected double mass;


    public Matter() {
        color = defaultColor;
    }

    public Matter(Color c) {
        color = c;
    }


    /*
     * GETTERS:
     */

    // EFFECTS: returns the state of Matter
    public String getState() {
        return state;
    }

    // EFFECTS: returns the color of the Matter
    public Color getColor() {
        return color;
    }

    // EFFECTS: returns mass of Matter
    public abstract double getMass();

    // EFFECTS: returns volume of Matter, in pixels cubed
    public abstract double getVolume();

    // EFFECTS: returns the density of Matter
    public abstract double getDensity();

}
