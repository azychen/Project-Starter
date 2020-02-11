package model.matter;

import javafx.scene.shape.CircleBuilder;
import model.Sandbox;

import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Circle extends Solid {

    public static final double PI = 3.1415926535;
    private static final double defaultRadius = 1.0;

    private double radius;

    public Circle(double m, Color c, double x, double y, int r) {
        super(m, c, x, y);
        radius = r;
        volume = getVolume();
    }

    public Circle() {
        super();
        radius = defaultRadius;
        volume = getVolume();
    }

    @Override
    public double getVolume() {
        return PI * radius * radius * depth;
    }


    // EFFECTS: returns the radius of the circle

    public double getRadius() {
        return radius;
    }




    /*
     * PHYSICAL INTERACTIONS
     */


    // EFFECTS: returns true if this has collided with other
    public boolean collision(Circle c) {
        double distanceBetweenX = pow((getPosX() - c.getPosX()), 2);
        double distanceBetweenY = pow((getPosY() - c.getPosY()), 2);
        double distance = sqrt(distanceBetweenX + distanceBetweenY);
        return (distance * Sandbox.pixelsToMeters < (getRadius() + c.getRadius()));
    }

    // REQUIRES: both circles have collided
    // MODIFIES: this and other
    // EFFECTS:  through conservation of momentum, the speed of this and other are
    //           changed accordingly
    public void bounce(Circle c) {

    }


    // MODIFIES: this
    // EFFECTS: if ball intersects with wall, the ball bounces in the opposite direction

    public void bounceWall() {
        bounceWallBot();
        bounceWallTop();
        bounceWallLeft();
        bounceWallRight();
    }

    // BOUNCING

    // MODIFIES: this
    // EFFECTS: if ball is touching top wall, ball bounces
    private void bounceWallTop() {
        if (posY - radius <= 0.0) {
            speedY = -speedY * conserved;
        }
    }

    // MODIFIES: this
    // EFFECTS: if ball is touching bottom wall, ball bounces
    private void bounceWallBot() {
        if (posY + radius >= Sandbox.HEIGHT) {
            speedY = -speedY * conserved;
        }
    }


    // MODIFIES: this
    // EFFECTS: if ball is touching left wall, ball bounces
    private void bounceWallLeft() {
        if (posX - radius <= 0.0) {
            speedX = -speedX * conserved;
        }
    }

    // MODIFIES: this
    // EFFECTS: if ball is touching right wall, ball bounces
    private void bounceWallRight() {
        if (posX + radius >= Sandbox.WIDTH) {
            speedX = -speedX * conserved;
        }
    }


}
