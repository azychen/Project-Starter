package ui;

import model.matter.Ball;


public class Main {

    public static void main(String[] args) {

        BallPitWindow window = new BallPitWindow();

        Ball b1 = new Ball(1000, 3.0, 2.0, 0.25);
        Ball b2 = new Ball(3, 2.8, 2.6, 0.25);

        window.addBall(b1);
        window.addBall(b2);

        window.printInfo();

        window.moveDemo();
        window.addDemo();
        window.removeDemo();
        window.interactDemo();


    }
}
