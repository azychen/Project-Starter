package model;

import model.matter.Ball;

import java.util.ArrayList;
import java.util.List;

import static model.matter.Matter.conserved;

/*
 *  This class represents the environment containing all the Matter.
 *  It handles all of the physical interactions.
 */

public class BallPit {

    // CONSTANTS
    public static final double pixelsToMeters = 0.01;
    public static final double tickRate = 0.02;   // 50 frames per second
    public static final double gravity = -9.81;
    public static final double WIDTH = 12.0;
    public static final double HEIGHT = 8.0;


    private List<Ball> balls;

    public BallPit() {
        balls = new ArrayList<>();
    }


    // GETTERS

    // EFFECTS: returns the list of Balls int the BallPit
    public List<Ball> getBalls() {
        return balls;
    }

    // SETTERS

    // MODIFIES: this
    // EFFECTS: adds given Ball to the BallPit
    public void addBall(Ball b) {
        balls.add(b);
    }

    // MODIFIES: this
    // EFFECTS: removes given Ball from the BallPit, returns true if successful
    public void removeBall(Ball b) {
        balls.removeIf(ball -> b == ball);
    }

    // MODIFIES: this
    // EFFECTS: clears BallPit of all matter
    public void clearBallPit() {
        balls.clear();
    }



    // MODIFIES: this
    // EFFECTS: moves Matter, checks for collisions
    public void nextState() {
        moveBalls();
        handleWalls();
        handleCollisions();
    }


    // MODIFIES: this
    // EFFECTS: moves all Matter
    private void moveBalls() {
        for (Ball b : balls) {
            moveBall(b);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the given Ball in the BallPit by a specified amount
    //          The function for displacement as a function of time is:
    //          x(t) = x_0 + vt + 1/2*at^2, where a = g;
    private void moveBall(Ball s) {
        s.setPosX(s.getPosX() + s.getSpeedX() * tickRate);
        s.setPosY(s.getPosY() + s.getSpeedY() * tickRate);
        s.setSpeedY(s.getSpeedY() + gravity * tickRate);
    }

    // MODIFIES: this
    // EFFECTS: moves Balls accordingly if colliding with other Balls.
    private void handleCollisions() {
        if (balls.size() <= 1) {
            return;
        }
        for (Ball b : balls) {
            handleCollision(b, balls);
        }
    }

    // MODIFIES: this and other
    // EFFECTS: if ball collides with another ball in balls, handles collision
    private void handleCollision(Ball b, List<Ball> lob) {
        for (Ball c : lob) {
            if (b != c && b.collision(c)) {
                b.bounce(c);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: moves Ball accordingly if it is in contact with wall
    private void handleWalls() {
        for (Ball b : balls) {
            bounceWall(b);
        }
    }

    // BOUNCING

    // MODIFIES: this
    // EFFECTS: if ball intersects with wall, the ball bounces in the opposite direction
    private void bounceWall(Ball b) {
        bounceWallBot(b);
        bounceWallTop(b);
        bounceWallLeft(b);
        bounceWallRight(b);
    }

    // MODIFIES: this
    // EFFECTS: if ball is touching bot wall, ball bounces
    private void bounceWallBot(Ball b) {
        if (b.getPosY() - b.getRadius() <= 0.01) {
            b.setPosY(b.getRadius() + 0.02);
            b.setSpeedY(-b.getSpeedY() * conserved);
        }
    }

    // MODIFIES: this
    // EFFECTS: if ball is touching top wall, ball bounces
    private void bounceWallTop(Ball b) {
        if (b.getPosY() + b.getRadius() >= HEIGHT - 0.01) {
            b.setPosY(HEIGHT - b.getRadius() - 0.02);
            b.setSpeedY(-b.getSpeedY() * conserved);
        }
    }


    // MODIFIES: this
    // EFFECTS: if ball is touching left wall, ball bounces
    private void bounceWallLeft(Ball b) {
        if (b.getPosX() - b.getRadius() <= 0.01) {
            b.setPosX(b.getRadius() + 0.02);
            b.setSpeedX(-b.getSpeedX() * conserved);
        }
    }

    // MODIFIES: this
    // EFFECTS: if ball is touching right wall, ball bounces
    private void bounceWallRight(Ball b) {
        if (b.getPosX() + b.getRadius() >= HEIGHT - 0.01) {
            b.setPosX(WIDTH - b.getRadius() - 0.02);
            b.setSpeedX(-b.getSpeedX() * conserved);
        }
    }

}
