package model.matter;

import model.BallPit;
import model.ImpossibleValueException;
import ui.Main;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.*;

/*
 *      This class represents the ball inside of the BallPit.
 *      It has various physical and UI properties.
 */


public class Ball extends Matter {

    public static final double PI = 3.1415926535;

    // DEFAULT PARAMETERS
    public static final double defaultRadius = 0.25;
    public static final double defaultMass = 5.0;

    public static final double defaultPosX = BallPit.WIDTH / 2;
    public static final double defaultPosY = BallPit.HEIGHT / 2;

    protected static int nextIndex = 1;
    protected int index;

    public static final int LAUNCH_RATIO = 20;
    protected static final double BUFFER_DIST = 0.03;

    private double radius;

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball(double m, double x, double y, double r, double dx, double dy, int i) throws ImpossibleValueException {
        super();
        if (m <= 0 || r <= 0) {
            throw new ImpossibleValueException();
        }
        mass = m;
        posX = x;
        posY = y;
        speedX = dx;
        speedY = dy;
        index = i;
        radius = r;
        volume = getVolume();
    }

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball(double m, double x, double y, double r, double dx, double dy, Color c) throws ImpossibleValueException {
        color = c;
        if (m <= 0 || r <= 0) {
            throw new ImpossibleValueException();
        }
        mass = m;
        posX = x;
        posY = y;
        speedX = dx;
        speedY = dy;
        index = nextIndex++;
        radius = r;
        volume = getVolume();
    }

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball(double m, double x, double y, double r) throws ImpossibleValueException {
        super();
        if (m <= 0 || r <= 0) {
            throw new ImpossibleValueException();
        }
        mass = m;
        posX = x;
        posY = y;
        speedX = 0;
        speedY = 0;
        index = nextIndex++;
        radius = r;
        volume = getVolume();
    }

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball(double m, double r) throws ImpossibleValueException {
        super();
        if (m <= 0 || r <= 0) {
            throw new ImpossibleValueException();
        }
        mass = m;
        radius = r;
        setRandomLocation();
        speedX = 0;
        speedY = 0;
        index = nextIndex++;
        volume = getVolume();
    }

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball(double m, double r, Color c) throws ImpossibleValueException {
        super(c);
        if (m <= 0 || r <= 0) {
            throw new ImpossibleValueException();
        }
        mass = m;
        radius = r;
        setRandomLocation();
        speedX = 0;
        speedY = 0;
        index = nextIndex++;
        volume = getVolume();
    }

    // REQUIRES: mass > 0
    // EFFECTS: constructs a Ball with user-defined parameters.
    public Ball() {
        super();
        mass = defaultMass;
        radius = defaultRadius;
        setRandomLocation();
        speedX = 0;
        speedY = 0;
        index = nextIndex++;
        volume = getVolume();
    }

    // EFFECTS: returns the x-coordinate of the Ball
    public double getPosX() {
        return posX;
    }

    // EFFECTS: returns the x-coordinate of the Ball
    public double getPosY() {
        return posY;
    }

    // EFFECTS: returns the magnitude of the Ball speed
    public double getSpeed() {
        return sqrt(speedX * speedX + speedY * speedY);
    }

    // EFFECTS: returns the x-speed of the Ball
    public double getSpeedX() {
        return speedX;
    }

    // EFFECTS: returns the y-speed of the Ball
    public double getSpeedY() {
        return speedY;
    }


    // EFFECTS: returns the index of the Ball
    public int getIndex() {
        return index;
    }



    // METHODS


    // REQUIRES: new posX must be in range [radius, WIDTH-radius];
    // MODIFIES: this
    // EFFECTS: sets posX
    public void setPosX(double x) {
        posX = x;
    }

    // REQUIRES: new posY must be in range [radius, HEIGHT-radius];
    // MODIFIES: this
    // EFFECTS: sets posY
    public void setPosY(double y) {
        posY = y;
    }

    // MODIFIES: this
    // EFFECTS: increases/decreases speedX
    public void setSpeedX(double x) {
        speedX = x;
    }

    // MODIFIES: this
    // EFFECTS: increases/decreases speedY
    public void setSpeedY(double y) {
        speedY = y;
    }



    @Override
    // EFFECTS: returns the mass of the Ball
    public double getMass() {
        return mass;
    }

    @Override
    // EFFECTS: returns the volume of the Ball
    public double getVolume() {
        return PI * radius * radius * depth;
    }

    @Override
    // EFFECTS: returns the density of the Ball
    public double getDensity() {
        return mass / (Ball.PI * radius * radius * Matter.depth);
    }

    // EFFECTS: returns the radius of the circle
    public double getRadius() {
        return radius;
    }

    /*
     * PHYSICAL INTERACTIONS
     */

    // EFFECTS: returns the distance between the centers of two balls
    public double getDistance(Ball c) {
        double distanceBetweenX = pow((getPosX() - c.getPosX()), 2);
        double distanceBetweenY = pow((getPosY() - c.getPosY()), 2);
        return sqrt(distanceBetweenX + distanceBetweenY);
    }

    // EFFECTS: returns true if this has collided with other, false otherwise
    public boolean collision(Ball c) {
        return (getDistance(c)  < (radius + c.getRadius()));
    }

    // REQUIRES: both balls have collided, at least one is moving,
    //           and are not the same ball
    // MODIFIES: this and other
    // EFFECTS:  through conservation of momentum in one and two dimensions,
    //           the speed of this and other are changed accordingly
    // SOURCE: formulas to calculate final velocities was found on Wikipedia:
    //         https://en.wikipedia.org/wiki/Elastic_collision
    public void bounce(Ball c) {
        if (posX < c.getPosX()) {
            handleBounce(c);
        } else {
            c.handleBounce(this);
        }
    }


    // REQUIRES: this is to the left of c
    // MODIFIES: this, c
    // EFFECTS:  changes velocities of this and other according to a
    //           two-dimensional collision along the X axis
    private void handleBounce(Ball c) {
        Main.playCollisionSound();
        double theta = (abs(posX - c.getPosX()) <= 0)
                ? PI / 2 : atan((posY - c.getPosY()) / (posX - c.getPosX()));
        double u1 = getSpeed();
        double u2 = c.getSpeed();
        double m1 = mass;
        double m2 = c.getMass();
        double v1 = getFirstSpeed(u1, u2, m1, m2);
        double v2 = getSecondSpeed(u1, u2, m1, m2);
        setNewSpeeds(v1, v2, theta, c);
        separateBalls(c);
    }

    // EFFECTS: returns new speed of first ball after collision
    private static double getFirstSpeed(double u1, double u2, double m1, double m2) {
        return conserved * (u1 * (m1 - m2) / (m1 + m2)
                + 2 * u2 * m2 / (m1 + m2));
    }

    // EFFECTS: returns new speed of second ball after collision
    private static double getSecondSpeed(double u1, double u2, double m1, double m2) {
        return conserved * (2 * m1 * u1 / (m1 + m2)
                + u2 * (m2 - m1) / (m1 + m2));
    }

    // EFFECTS: sets new speeds after collision
    private void setNewSpeeds(double v1, double v2, double theta, Ball c) {
        setSpeedX(v1 * cos(PI + theta));
        setSpeedY(v1 * sin(PI + theta));
        c.setSpeedX(v2 * cos(theta));
        c.setSpeedY(v2 * sin(theta));
    }


    // MODIFIES: this
    // EFFECTS: separates two balls
    private void separateBalls(Ball b) {
        double theta = atan((posY - b.getPosY()) / (posX - b.getPosX()));
        double overlap = (radius + b.getRadius() - getDistance(b)) / 2;
        setPosX(posX - overlap * cos(theta) - BUFFER_DIST);
        setPosY(posY - overlap * sin(theta) - BUFFER_DIST);
        b.setPosX(b.getPosX() + overlap * cos(theta) + BUFFER_DIST);
        b.setPosY(b.getPosY() + overlap * sin(theta) + BUFFER_DIST);
    }

    // MODIFIES: this
    // EFFECTS: sets random coordinate for ball to be spawned at.
    private void setRandomLocation() {
        Random n = new Random();
        setPosX(radius + (BallPit.WIDTH - radius) * n.nextDouble());
        setPosY(radius + (BallPit.HEIGHT - radius) * n.nextDouble());
    }

    // EFFECTS: returns true if given coordinate is inside ball
    public boolean inside(double x, double y) {
        return sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y)) < radius;
    }

    // MODIFIES: this
    // EFFECTS: launches ball based on click location
    public void launch(double mx, double my) {
        setSpeedX(speedX + LAUNCH_RATIO * (posX - mx) / radius);
        setSpeedY(speedY + LAUNCH_RATIO * (posY - my) / radius);
    }

}
