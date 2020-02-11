package model;

import model.matter.Liquid;
import model.matter.Matter;
import model.matter.Solid;

import java.util.ArrayList;
import java.util.List;

/*
 *  This class represents the environment containing all the Matter.
 *  It handles all of the physical interactions.
 */

public class Sandbox {
    public static double pixelsToMeters = 0.01;
    public static double tickRate = 0.02;   // 50 frames per second

    // CONSTANTS
    public static final double WIDTH = 12.0;
    public static final double HEIGHT = 8.0;

    private static final int updatePeriod = 10;     //
    static final int gravity = 10;                  // gravitational acceleration

    private List<Solid> solids;
    private Liquid liquid;


    public Sandbox() {
        solids = new ArrayList<Solid>();
    }


    // GETTERS

    // EFFECTS: returns the list of Solids int the Sandbox
    public List<Solid> getSolids() {
        return solids;
    }

    // EFFECTS: returns true if there is a Liquid in the Sandbox
    public boolean isThereLiquid() {
        return liquid != null;
    }

    // REQUIRES: there must be a Liquid present in the Sandbox
    // EFFECTS: returns the Liquid in the Sandbox
    public Liquid getLiquid() {
        return liquid;
    }



    // SETTERS

    // EFFECTS: adds given Solid to the Sandbox
    public void addSolid(Solid s) {
        solids.add(s);
    }


    // MODIFIES: this
    // EFFECTS: moves Matter, checks for collisions
    public void nextState() {
        moveMatter();
        checkCollisions();
    }

    // MODIFIES: this
    // EFFECTS: clears Sandbox of all matter
    public void clearSandbox() {
        solids.clear();
        liquid = null;
    }

    // MODIFIES: this
    // EFFECTS: moves all Matter
    public void moveMatter() {
        for (Solid s : solids) {
            moveSolid(s);
        }
    }



    // MODIFIES: this
    // EFFECTS: moves the given Solid in the Sandbox by a specified amount
    //          The function for displacement as a function of time is:
    //          x(t) = x_0 + vt + 1/2*at^2, where a = g;
    private void moveSolid(Solid s) {
        s.addSpeed(s.getSpeedX() * tickRate - 0.5 * tickRate * tickRate,
                s.getSpeedY() * tickRate);
    }



    // MODIFIES: this
    // EFFECTS: moves Matter accordingly if colliding with other matter or Sandbox boundaries
    private void checkCollisions() {

    }






//    // REQUIRES: there be a liquid in the sandbox
//    // MODIFIES: this
//    // EFFECTS: moves liquid level up based on any submerged solids
//    private void moveLiquid() {
//        double displacedVolume = 0;
//        for (Solid s : solids) {
//            if (s.isSubmerged()) {
//                displacedVolume += s.getVolume() * s.getFractionSubmerged();
//            }
//        }
//        liquid.setLevel(displacedVolume / Matter.depth / (WIDTH * pixelsToMeters)); //!!!
//    }



}
