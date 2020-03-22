//package ui;
//
//import model.*;
//import model.matter.Ball;
//import persistence.Reader;
//import persistence.Writer;
//
//import java.awt.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.text.DecimalFormat;
//import java.util.List;
//import java.util.Objects;
//import java.util.Scanner;
//
///*
// *      This class is the representation of what is shown on the screen.
// *      At the moment, it simply outputs a console representation of the
// *      Balls in the BallPit.
// *
// *      Future feature: click on a ball, and it will launch it.
// */
//
//public class BallPitWindowConsole {
//
//    protected BallPit ballPit;
//    protected Color backgroundColor = Color.WHITE;
//    protected static final String BALLPITS_FILE = "./data/ballpits.txt";
//    protected static DecimalFormat round = new DecimalFormat("0.00");
//
//    protected Scanner input;
//
//
//    // EFFECTS: creates a window with a new ballpit
//    public BallPitWindowConsole() {
//        ballPit = new BallPit();
//        welcomeMessage();
//    }
//
//    // EFFECTS: creates a window with a pre-existing ballpit
//    public BallPitWindowConsole(BallPit ballPit) {
//        this.ballPit = ballPit;
//        welcomeMessage();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: runs the main menu selection
//    public void runMainMenu() {
//        input = new Scanner(System.in);
//        String command;
//
//        while (true) {
//            printMainMenuOptions();
//            command = input.next().toLowerCase();
//
//            if (command.equals("q")) {
//                break;
//            } else {
//                processMainMenuInput(command);
//            }
//        }
//        goodbyeMessage();
//    }
//
//    // EFFECTS: prints main menu options
//    protected void printMainMenuOptions() {
//        System.out.println("What would you like to do? Enter command... \n");
//        System.out.println("\t[s] - start new sandbox");
//        System.out.println("\t[l] - load a saved sandbox");
//        System.out.println("\t[q] - quit sandbox");
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: selects use command in main menu
//    protected void processMainMenuInput(String input) {
//        switch (input) {
//            case "s":
//                newSandbox();
//                break;
//            case "l":
//                loadSandbox();
//                break;
//            default:
//                System.out.println("Invalid input");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: runs the in-sandbox menu options
//    protected void runSandboxMenu() {
//        input = new Scanner(System.in);
//        String command;
//
//        while (true) {
//            printSandboxMenuOptions();
//            command = input.next().toLowerCase();
//
//            if (command.equals("q")) {
//                System.out.println("Would you like to save " + ballPit.getName() + "? (y/n)\n");
//                command = input.next().toLowerCase();
//                if (command.equals("y")) {
//                    processSavePit();
//                    break;
//                } else if (!command.equals("n")) {
//                    System.out.println("Invalid input.\n");
//                } else {
//                    break;
//                }
//            } else {
//                processSandboxMenuInput(command);
//            }
//        }
//        System.out.println("Successfully exited to main menu.\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: starts new sandbox
//    protected void newSandbox() {
//
//        input = new Scanner(System.in);
//        String command;
//
//        System.out.println("Would you like to give your sandbox a name? (y/n)");
//
//        command = input.next().toLowerCase();
//        switch (command) {
//            case "n":
//                System.out.println("Creating new Ball Pit... ");
//                ballPit = new BallPit();
//                break;
//            case "y":
//                System.out.println("What would you like to name your ball pit? ");
//                String newName = input.next();
//
//                System.out.println("Creating " + newName + "\n");
//                ballPit = new BallPit(newName);
//        }
//        runSandboxMenu();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads sandbox
//    protected void loadSandbox() {
//        try {
//            List<BallPit> pits = Reader.readBallPits(new File(BALLPITS_FILE));
//            if (pits.size() < 1) {
//                System.out.println("There are no saved ball pits!"
//                        + "Why not make a new one?\n");
//                return;
//            }
//            System.out.println("Which ball pit would you like to load? Enter number...\n");
//            printBallPits(pits);
//            int command = input.nextInt();
//            if (command > pits.size() + 1) {
//                throw new IOException();
//            }
//            ballPit = pits.get(command - 1);
//            System.out.println("Successfully loaded " + ballPit.getName() + ".\n");
//            runSandboxMenu();
//        } catch (IOException e) {
//            System.out.println("That ball pit doesn't exist!\n");
//        }
//    }
//
//    // EFFECTS: prints the names of each Ball Pit in a list of Ball Pits
//    protected void printBallPits(List<BallPit> pits) {
//        for (int i = 0; i < pits.size(); ++i) {
//            System.out.println("\t#" + (i + 1) + ": " + pits.get(i).getName());
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: selects use command in main menu
//    protected void processSandboxMenuInput(String input) {
//        switch (input) {
//            case "a":
//                processAddBall();
//                break;
//            case "d":
//                processDeleteBall();
//                break;
//            case "v":
//                processViewBalls();
//                break;
//            case "p":
//                processAdvancePit();
//                break;
//            case "c":
//                processClear();
//                break;
//            case "s":
//                processSavePit();
//                break;
//            default:
//                System.out.println("Invalid input.\n");
//        }
//    }
//
//    // EFFECTS: prints main menu options
//    protected void printSandboxMenuOptions() {
//        System.out.println("You are inside " + ballPit.getName() + ". Enter command...\n");
//        System.out.println("\t[a] - add ball");
//        System.out.println("\t[d] - delete ball");
//        System.out.println("\t[v] - view balls");
//        System.out.println("\t[p] - advance ball pit");
//        System.out.println("\t[c] - clear balls from ball pit");
//        System.out.println("\t[s] - save ball pit");
//        System.out.println("\t[q] - quit ball pit\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user request to add ball
//    protected void processAddBall() {
//        double mass;
//        System.out.println("How heavy would you like your ball to be? "
//                + "Enter mass (kg) ...\n");
//        mass = input.nextDouble();
//
//        double radius;
//        System.out.println("What radius would you like your ball to be? "
//                + "Enter radius (m)...\n");
//        radius = input.nextDouble();
//
//        Ball inputBall = new Ball(mass, radius);
//        ballPit.addBall(inputBall);
//        System.out.println("Ball successfully added!\n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user request to add ball
//    protected void processDeleteBall() {
//        printInfo();
//        if (getBallPit().getBalls().size() == 0) {
//            System.out.println("Can't delete any balls if there are none.\n ");
//            return;
//        }
//        System.out.println("Which ball would you like to delete? Enter ball number...\n");
//        int index;
//        index = input.nextInt();
//        if (getBallByIndex(index) != null) {
//            deleteBall(Objects.requireNonNull(getBallByIndex(index)));
//            System.out.println("Successfully deleted Ball #" + index + ".\n");
//        } else {
//            System.out.println("Unsuccessful deleting. Perhaps the ball isn't in the pit? ");
//        }
//
//    }
//
//    // EFFECTS: processes user request to view ball;
//    protected void processViewBalls() {
//        printInfo();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user request to advance ball pit;
//    protected void processAdvancePit() {
//        double secs;
//        System.out.println("How many seconds would you like to advance ball pit by? "
//                + "Enter seconds...\n");
//        secs = input.nextDouble();
//        advanceBallPit(secs);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user request to add ball
//    protected void processClear() {
//        if (ballPit.getBalls().size() > 0) {
//            ballPit.clearBallPit();
//            System.out.println("Ball pit successfully cleared.\n");
//        } else {
//            System.out.println("There are already no balls in the pit!\n");
//        }
//    }
//
//    // EFFECTS: processes user request to save current ball pit
//    protected void processSavePit() {
//        try {
//            System.out.println("Attempting to save " + ballPit.getName() + "...\n");
//            Writer writer = new Writer(new File(BALLPITS_FILE));
//            writer.write(ballPit);
//            writer.close();
//            System.out.println("Successfully saved Ball Pit " + ballPit.getName()
//                    + " to " + BALLPITS_FILE);
//        } catch (FileNotFoundException e) {
//            System.out.println("ERROR: file not found");
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("ERROR: unsupported encoding");
//            e.printStackTrace();
//        }
//    }
//
//    // EFFECTS: prints information relating to the matter in the BallPit
//    protected void printInfo() {
//        if (ballPit.getBalls().size() == 0) {
//            System.out.println("There are no balls in the pit! Why not add some? \n");
//            return;
//        }
//        for (Ball m : ballPit.getBalls()) {
//            System.out.println();
//            System.out.println("Ball #" + m.getIndex() + ": at ("
//                    + round.format(m.getPosX()) + ", " + round.format(m.getPosY()) + ") m.");
//            System.out.println("Mass: " + round.format(m.getMass()) + " kg.");
//            System.out.println("Volume: " + round.format(m.getVolume()) + " m^3.");
//            System.out.println("Density: " + round.format(m.getDensity()) + " kg/m^3. ");
//            System.out.println("Current speed: " + round.format(m.getSpeed()) + " m/s \n");
//        }
//    }
//
//    // EFFECTS: prints out the coordinates of the balls
//    // SOURCE: https://stackoverflow.com/questions/699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java
//    //         for print formatting
//    protected void printCoords() {
//        for (Ball b : ballPit.getBalls()) {
//            System.out.printf("%-20s \t",
//                    "#" + b.getIndex() + ": "
//                    + "(" + round.format(b.getPosX()) + ", "
//                    + round.format(b.getPosY()) + ")");
//        }
//        System.out.println(" ");
//    }
//
//
//    // EFFECTS: returns the BallPit
//    public BallPit getBallPit() {
//        return ballPit;
//    }
//
//    // MODIFIES: this
//    // EFFECTS: clears the ball pit
//    protected void clearWindow() {
//        System.out.println("Clearing window... \n");
//        ballPit.clearBallPit();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: advances BallPit by specified amount of seconds
//    protected void advanceBallPit(double s) {
//        System.out.println("\n Advancing state. Printing coordinates: \n");
//        for (int i = 0; i < s / BallPit.tickRate; ++i) {
//            if (i % 5 == 0) {
//                printCoords();
//            }
//            getBallPit().nextState();
//        }
//        System.out.println("\n Here are the balls after " + s + " seconds:\n");
//        printInfo();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: uses the add tool to add a ball to the BallPit.
//    public void addBall(Ball b) {
//        ballPit.addBall(b);
//        System.out.println("Adding ball #" + b.getIndex() + "... \n");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: uses the delete tool to delete a ball to the BallPit.
//    public void deleteBall(Ball b) {
//        System.out.println("Deleting ball #" + b.getIndex() + "... \n");
//        ballPit.removeBall(b);
//    }
//
//    // EFFECTS: gets ball by index
//    private Ball getBallByIndex(int index) {
//        for (Ball b : ballPit.getBalls()) {
//            if (b.getIndex() == index) {
//                return b;
//            }
//        }
//        return null;
//    }
//
//    // EFFECTS: prints out a welcome message once ball pit is created
//    public void welcomeMessage() {
//        System.out.println("Welcome to the Ball Pit! \n");
//    }
//
//    // EFFECTS: prints out a goodbye message once ball pit is created
//    public void goodbyeMessage() {
//        System.out.println("See you next time! \n");
//    }
//}
