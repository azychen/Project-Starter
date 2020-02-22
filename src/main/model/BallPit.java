package model;

import model.matter.Ball;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static model.matter.Matter.conserved;

/*
 *      This class represents the environment containing all the Balls.
 *      It handles all of the physical interactions.
 */

public class BallPit implements Saveable {

    // CONSTANTS
    public static final double pixelsToMeters = 0.01;
    public static final double tickRate = 0.02;         // 50 frames per second
    public static final double gravity = -9.81;
    public static final double WIDTH = 12.0;
    public static final double HEIGHT = 8.0;

    private List<Ball> balls;
    private String name;


    // EFFECTS: creates new ball pit with default name
    public BallPit() {
        name = "My Ball Pit";
        balls = new ArrayList<>();
    }

    // EFFECTS: creates new ball pit with given name;
    public BallPit(String name) {
        balls = new ArrayList<>();
        this.name = name;
    }

    // EFFECTS: creates ball pit with name and existing list of balls;
    public BallPit(String name, List<Ball> balls) {
        this.balls = balls;
        this.name = name;
    }


    // EFFECTS: returns the list of Balls int the BallPit
    public List<Ball> getBalls() {
        return balls;
    }

    // EFFECTS: returns the name of the BallPit
    public String getName() {
        return name;
    }


    // REQUIRES: ball is not already in the ball pit
    // MODIFIES: this
    // EFFECTS: adds given Ball to the BallPit
    public void addBall(Ball b) {
        balls.add(b);
    }

    // MODIFIES: this
    // EFFECTS: removes given Ball from the BallPit if it is in the BallPit.
    // SOURCE: i used an if statement initially to find the ball,
    //          but intellij suggested this as an improvement, so
    //          i went with it.
    public void removeBall(Ball b) {
        balls.removeIf(ball -> b == ball);
    }

    // MODIFIES: this
    // EFFECTS: clears BallPit of all balls
    public void clearBallPit() {
        balls.clear();
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the ball pit to a new given name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: advances to the next frame in the BallPit
    public void nextState() {
        moveBalls();
        handleWalls();
        handleCollisions();
    }


    // MODIFIES: this
    // EFFECTS: moves all Balls
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
        if (balls.size() > 1) {
            for (int i = 0; i < balls.size(); ++i) {
                for (int j = i + 1; j < balls.size(); ++j) {
                    if (balls.get(i).collision(balls.get(j))) {
                        balls.get(i).bounce(balls.get(j));
                    }
                }
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

    // MODIFIES: this
    // EFFECTS: if ball intersects with wall, the ball bounces in the opposite
    //          direction
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
        if (b.getPosX() + b.getRadius() >= WIDTH - 0.01) {
            b.setPosX(WIDTH - b.getRadius() - 0.02);
            b.setSpeedX(-b.getSpeedX() * conserved);
        }
    }


    // EFFECTS: prints ball pit data to print writer
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        for (int i = 0; i < balls.size(); ++i) {

            printWriter.print(balls.get(i).getMass());
            printWriter.print(Reader.DELIMITER);

            printWriter.print(balls.get(i).getPosX());
            printWriter.print(Reader.DELIMITER);

            printWriter.print(balls.get(i).getPosY());
            printWriter.print(Reader.DELIMITER);

            printWriter.print(balls.get(i).getRadius());
            printWriter.print(Reader.DELIMITER);

            printWriter.print(balls.get(i).getSpeedX());
            printWriter.print(Reader.DELIMITER);

            printWriter.print(balls.get(i).getSpeedY());
            printWriter.print(Reader.DELIMITER);

            if (i < balls.size() - 1) {
                printWriter.print(balls.get(i).getIndex());
                printWriter.print(Reader.DELIMITER);
            } else {
                printWriter.println(balls.get(i).getIndex());
            }

        }
    }
}
