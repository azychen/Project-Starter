package model.matter;

import java.awt.*;

import static java.lang.Math.random;

/*
 *      This class represents all of the objects that can be seen in the BallPit.
 *      It includes properties such as mass, volume, position, speed, etc.
 *      At the moment, the only type of Matter is Ball, but there is more to come.
 */

public abstract class Matter {

    // DEFAULT PARAMETERS
    public static final double depth = 1.0;
    public static final double conserved = 0.65;

    // COMMON PARAMETERS
    protected Color color;

    protected double volume;
    protected double mass;

    // UI PROPERTIES, all in m
    protected double posX;
    protected double posY;

    protected double speedX;
    protected double speedY;


    // EFFECTS: creates Matter with random colour;
    // SOURCE: https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
    public Matter() {
        color = new Color((int)(random() * 0x1000000));
    }

    public Matter(Color c) {
        color = c;
    }


    /*
     * GETTERS:
     */

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
