package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.matter.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 *      This is the test class for BallPit. It tests all of the public
 *      methods in the class!
 */



public class BallPitTest {

    private BallPit testPit;
    private Ball b1;
    private Ball b2;
    private Ball b3;

    @BeforeEach
    public void setup() {
        testPit = new BallPit("Anton's Ball Pit");
        b1 = new Ball();
        b2 = new Ball(8, 2.0, 2.0, 0.2);
        testPit.addBall(b1);
        testPit.addBall(b2);
        b3 = new Ball();
    }

    @Test
    public void testBallPit() {
        assertEquals(testPit.getBalls().size(), 2);
        assertEquals("Anton's Ball Pit", testPit.getName());
    }

    @Test
    public void testBallPitWithExistingBalls() {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            balls.add(new Ball());
        }
        BallPit myPit = new BallPit("myPit", balls);
        assertEquals("myPit", myPit.getName());
        assertEquals(5, myPit.getBalls().size());
    }

    @Test
    public void testGetBalls() {
        assertEquals(testPit.getBalls().get(0), b1);
        assertEquals(testPit.getBalls().get(1), b2);
        assertEquals(testPit.getBalls().size(), 2);
    }

    @Test
    public void testGetName() {
        assertEquals("Anton's Ball Pit", testPit.getName());
    }

    @Test
    public void testSetName() {
        assertEquals("Anton's Ball Pit", testPit.getName());
        testPit.setName("Anton's Revised Ball Pit");
        assertEquals("Anton's Revised Ball Pit", testPit.getName());

    }

    @Test
    public void testAddBall() {
        testPit.addBall(b3);
        assertEquals(testPit.getBalls().size(), 3);
        assertEquals(testPit.getBalls().get(2), b3);
    }

    @Test
    public void testRemoveBallNoBall() {
        testPit.removeBall(b3);
        assertEquals(testPit.getBalls().get(0), b1);
        assertEquals(testPit.getBalls().get(1), b2);
        assertEquals(testPit.getBalls().size(), 2);
    }

    @Test
    public void testRemoveBallWithBall() {
        testPit.removeBall(b2);
        assertEquals(testPit.getBalls().get(0), b1);
        assertEquals(testPit.getBalls().size(), 1);
    }

    @Test
    public void testClearBallPit() {
        testPit.clearBallPit();
        assertEquals(testPit.getBalls().size(), 0);
    }

    @Test
    public void testClearBallPitAlreadyClear() {
        testPit = new BallPit();
        testPit.clearBallPit();
        assertEquals(testPit.getBalls().size(), 0);
    }

    @Test
    public void testNextStateMultipleBallsCollision() {
        testPit.clearBallPit();
        Ball nsb1 = new Ball(20, 3, 1, 1);
        Ball nsb2 = new Ball(20, 5, 1, 1);
        testPit.addBall(nsb1);
        testPit.addBall(nsb2);
        testPit.nextState();
        assertEquals(3, nsb1.getPosX(), 0.03);
        assertEquals(5, nsb2.getPosX(), 0.03);
        assertEquals(1, nsb1.getPosY(), 0.03);
        assertEquals(1, nsb2.getPosY(), 0.03);
        assertEquals(0, nsb1.getSpeedX());
        assertEquals(0, nsb2.getSpeedX());
        assertEquals(0, nsb1.getSpeedY(), 0.03);
        assertEquals(0, nsb2.getSpeedY(), 0.03);
    }



    @Test
    public void testMoveBalls() {
        testPit.nextState();

        assertEquals(-0.2838, testPit.getBalls().get(0).getSpeedY(), 0.01);
        assertEquals(0, testPit.getBalls().get(0).getSpeedX(), 0.01);

        assertEquals(-0.2838, testPit.getBalls().get(1).getSpeedY(), 0.01);
        assertEquals(0, testPit.getBalls().get(1).getSpeedX(), 0.01);
    }

    @Test
    public void testHandleWallsBottomLeft() {
        testPit.clearBallPit();
        Ball b4 = new Ball(20, 0.0, 0.0, 1);
        testPit.addBall(b4);
        testPit.addBall(b2);
        testPit.nextState();

        assertEquals(b4.getRadius(), b4.getPosY(), 0.04);
        assertEquals(b4.getRadius(), b4.getPosX(), 0.04);
        assertEquals(2.0, b2.getPosY());
        assertEquals(2.0, b2.getPosX());

    }

    @Test
    public void testHandleWallsTopRight() {
        testPit.clearBallPit();
        Ball b5 = new Ball(20, BallPit.WIDTH, BallPit.HEIGHT, 1);
        testPit.addBall(b5);
        testPit.addBall(b2);
        testPit.nextState();

        assertEquals(BallPit.HEIGHT - b5.getRadius(), b5.getPosY(), 0.04);
        assertEquals(BallPit.WIDTH - b5.getRadius(), b5.getPosX(), 0.04);
        assertEquals(2.0, b2.getPosY());
        assertEquals(2.0, b2.getPosX());
    }

    @Test
    void testEarthquakeUp() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.earthquakeUp();
        assertEquals(BallPit.EARTHQUAKE_SPEED, b1.getSpeedY());
        assertEquals(0, b1.getSpeedX());
    }

    @Test
    void testEarthquakeDown() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.earthquakeDown();
        assertEquals(-BallPit.EARTHQUAKE_SPEED, b1.getSpeedY());
        assertEquals(0, b1.getSpeedX());
    }

    @Test
    void testEarthquakeRight() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.earthquakeRight();
        assertEquals(BallPit.EARTHQUAKE_SPEED, b1.getSpeedX());
        assertEquals(0, b1.getSpeedY());
    }

    @Test
    void testEarthquakeLeft() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.earthquakeLeft();
        assertEquals(-BallPit.EARTHQUAKE_SPEED, b1.getSpeedX());
        assertEquals(0, b1.getSpeedY());
    }

    @Test
    void testLaunchNotInside() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.launch(5, 5);
        assertEquals(0, b1.getSpeedX());
        assertEquals(0, b1.getSpeedY());
    }

    @Test
    void testLaunchInside() {
        testPit.clearBallPit();
        Ball b1 = new Ball(1, 1, 1, 1);
        testPit.addBall(b1);
        testPit.launch(1, 0.001);
        assertEquals(0, b1.getSpeedX());
        assertEquals(Ball.LAUNCH_RATIO, b1.getSpeedY(), 0.1);
    }

    @Test
    void testSlowBall() {
        testPit.clearBallPit();
        Ball b = new Ball(1, 1, 1, 1, 1, 1, 1);
        testPit.addBall(b);
        testPit.slowBalls();
        assertEquals(0.9895, b.getSpeedX());
        assertEquals(0.9895, b.getSpeedY());
    }

    @Test
    void testToPixels() {
        assertEquals(100, BallPit.toPixels(1));
    }

    @Test
    void testToMeters() {
        assertEquals(1, BallPit.toMeters(100));
    }

    @Test
    public void testConstructorMassAndRadius() {
        Ball b = new Ball(1, 1);
        assertEquals(1, b.getMass());
        assertEquals(1, b.getRadius());
        assertEquals(0, b.getSpeedX());
        assertEquals(0, b.getSpeedY());
    }

    @Test
    public void testConstructorMassRadiusAndColor() {
        Ball b = new Ball(1, 1, Color.RED);
        assertEquals(1, b.getMass());
        assertEquals(1, b.getRadius());
        assertEquals(0, b.getSpeedX());
        assertEquals(0, b.getSpeedY());
        assertEquals(Color.RED, b.getColor());
    }
}
