package ui;

import model.*;
import model.matter.Ball;

import java.awt.*;
import java.text.DecimalFormat;

/*
 *      This class is the representation of what is shown on the screen.
 *      At the moment, it simply outputs a console representation of the
 *      Balls in the BallPit.
 *
 *      Future feature: click on a ball, and it will launch it.
 */

public class BallPitWindow {

    private BallPit ballPit;
    private Color backgroundColor = Color.WHITE;

    private static DecimalFormat round = new DecimalFormat("0.00");

    // EFFECTS: creates a window with a new ballpit
    public BallPitWindow() {
        ballPit = new BallPit();
        welcomeMessage();
    }

    // EFFECTS: creates a window with a pre-existing ballpit
    public BallPitWindow(BallPit ballPit) {
        this.ballPit = ballPit;
        welcomeMessage();
    }

    /*
     *         D E M O S
     */

    // MODIFIES: this
    // EFFECTS: plays the move demo
    public void moveDemo() {
        System.out.println("\n Here is a demo illustrating that the balls can move!");
        System.out.println("Particularly, this was my first story. \n");

        advanceBallPit(5);
    }

    // MODIFIES: this
    // EFFECTS: plays the add demo
    public void addDemo() {
        System.out.println("\n Here is a demo illustrating that I can add balls to the ball pit.");
        System.out.println("Particularly, this was my second story. \n");

        Ball b3 = new Ball();
        Ball b4 = new Ball(1000, 2);

        addBall(b3);
        addBall(b4);


        System.out.println("\n In the following, we see that we have added two more balls: \n ");
        printInfo();
    }

    // MODIFIES: this
    // EFFECTS: plays the remove demo
    public void removeDemo() {
        System.out.println("\n Here is a demo illustrating that we can remove balls from the ball pit. ");
        System.out.println("Particularly, this is my third story. \n");

        deleteBall(ballPit.getBalls().get(3));
        printInfo();

        System.out.println("We can see that ball #4 was removed. ");
        System.out.println("We can even remove all the balls at once! See: \n");

        clearWindow();
        printInfo();
    }

    // MODIFIES: this
    // EFFECTS: plays the interact demo
    public void interactDemo() {
        System.out.println("\n For the final demo, we will demonstrate that"
                + " the balls can interact with each other and the walls! ");
        System.out.println("Particularly, this is my fourth and final story. \n");

        Ball b5 = new Ball(30, 0.3);

        addBall(b5);
        advanceBallPit(5);

        System.out.println("\n Observe that as the ball reaches the bottom of the screen,"
                + " it bounces back up. \n");

        ballPit.clearBallPit();

        Ball b6 = new Ball(20, 3.1, 5.0, 0.5);
        Ball b7 = new Ball(8, 2.9, 4.0, 0.75);
        Ball b8 = new Ball(100, 3, 6, 1.5);

        addBall(b6);
        addBall(b7);
        addBall(b8);

        advanceBallPit(5);

        System.out.println("\n The balls collided with each other,"
                + " bouncing away from each other, obeying the conservation"
                + " laws of momentum. \n");

        System.out.println("That concludes my demo! ");

    }


    // EFFECTS: prints information relating to the matter in the BallPit
    public void printInfo() {
        if (ballPit.getBalls().size() == 0) {
            System.out.println("There are no balls in the pit! Why not add some? \n");
            return;
        }
        for (Ball m : ballPit.getBalls()) {
            System.out.println("Ball #" + m.getIndex() + ": at ("
                    + round.format(m.getPosX()) + ", " + round.format(m.getPosY()) + ") m.");
            System.out.println("Mass: " + round.format(m.getMass()) + " kg.");
            System.out.println("Volume: " + round.format(m.getVolume()) + " m^3.");
            System.out.println("Density: " + round.format(m.getDensity()) + " kg/m^3. ");
            System.out.println("Current speed: " + round.format(m.getSpeed()) + " m/s \n");
        }
    }

    // EFFECTS: prints out the coordinates of the balls
    // SOURCE: https://stackoverflow.com/questions/699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java
    //         for print formatting
    public void printCoords() {
        for (Ball b : ballPit.getBalls()) {
            System.out.printf("%-20s \t",
                    "#" + b.getIndex() + ": "
                    + "(" + round.format(b.getPosX()) + ", "
                    + round.format(b.getPosY()) + ")");
        }
        System.out.println(" ");
    }


    // EFFECTS: returns the BallPit
    public BallPit getBallPit() {
        return ballPit;
    }

    // MODIFIES: this
    // EFFECTS: clears the ball pit
    public void clearWindow() {
        System.out.println("Clearing window... \n");
        ballPit.clearBallPit();
    }

    // MODIFIES: this
    // EFFECTS: advances BallPit by specified amount of seconds
    public void advanceBallPit(int s) {
        System.out.println("\n Advancing state. Printing coordinates: \n");
        for (int i = 0; i < s / BallPit.tickRate; ++i) {
            if (i % 5 == 0) {
                printCoords();
            }
            getBallPit().nextState();
        }
        System.out.println("\n Here are the balls after " + s + " seconds:\n");
        printInfo();
    }

    // MODIFIES: this
    // EFFECTS: uses the add tool to add a ball to the BallPit.
    public void addBall(Ball b) {
        ballPit.addBall(b);
        System.out.println("Adding ball #" + b.getIndex() + "... \n");
    }

    // MODIFIES: this
    // EFFECTS: uses the delete tool to delete a ball to the BallPit.
    public void deleteBall(Ball b) {
        System.out.println("Deleting ball #" + b.getIndex() + "... \n");
        ballPit.removeBall(b);
    }

    // EFFECTS: prints out a welcome message once ball pit is created
    public void welcomeMessage() {
        System.out.println("Welcome to the Ball Pit! \n");
    }
}
