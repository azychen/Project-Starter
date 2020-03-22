//package ui;
//
//import model.matter.Ball;
//
//public class Demo extends BallPitWindowConsole {
//    /*
//     *         D E M O S
//     */
//
//    public Demo() {
//        super();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: runs the demo
//    public void runDemo() {
//        System.out.println("\n Starting demo... \n");
//        Ball b1 = new Ball(1000, 3.0, 2.0, 0.25);
//        Ball b2 = new Ball(3, 2.8, 2.6, 0.25);
//
//        addBall(b1);
//        addBall(b2);
//
//        printInfo();
//
//        moveDemo();
//        addDemo();
//        removeDemo();
//        interactDemo();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: plays the move demo
//    public void moveDemo() {
//        System.out.println("\n Here is a demo illustrating that the balls can move!");
//        System.out.println("Particularly, this was my first story. \n");
//
//        advanceBallPit(5);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: plays the add demo
//    public void addDemo() {
//        System.out.println("\n Here is a demo illustrating that I can add balls to the ball pit.");
//        System.out.println("Particularly, this was my second story. \n");
//
//        Ball b3 = new Ball();
//        Ball b4 = new Ball(1000, 2);
//
//        addBall(b3);
//        addBall(b4);
//
//
//        System.out.println("\n In the following, we see that we have added two more balls: \n ");
//        printInfo();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: plays the remove demo
//    public void removeDemo() {
//        System.out.println("\n Here is a demo illustrating that we can remove balls from the ball pit. ");
//        System.out.println("Particularly, this is my third story. \n");
//
//        deleteBall(ballPit.getBalls().get(3));
//        printInfo();
//
//        System.out.println("We can see that ball #4 was removed. ");
//        System.out.println("We can even remove all the balls at once! See: \n");
//
//        clearWindow();
//        printInfo();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: plays the interact demo
//    public void interactDemo() {
//        System.out.println("\n For the final demo, we will demonstrate that"
//                + " the balls can interact with each other and the walls! ");
//        System.out.println("Particularly, this is my fourth and final story. \n");
//
//        Ball b5 = new Ball(30, 0.3);
//
//        addBall(b5);
//        advanceBallPit(5);
//
//        System.out.println("\n Observe that as the ball reaches the bottom of the screen,"
//                + " it bounces back up. \n");
//
//        ballPit.clearBallPit();
//
//        Ball b6 = new Ball(20, 3.1, 5.0, 0.5);
//        Ball b7 = new Ball(8, 2.9, 4.0, 0.75);
//        Ball b8 = new Ball(100, 3, 6, 1.5);
//
//        addBall(b6);
//        addBall(b7);
//        addBall(b8);
//
//        advanceBallPit(5);
//
//        System.out.println("\n The balls collided with each other,"
//                + " bouncing away from each other, obeying the conservation"
//                + " laws of momentum. \n");
//
//        System.out.println("That concludes my demo! ");
//
//    }
//}
