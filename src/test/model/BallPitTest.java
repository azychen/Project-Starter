package model;

import java.awt.Color;
import model.matter.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallPitTest {

    private BallPit testPit;
    private Ball b1;
    private Ball b2;
    private Ball b3;

    @BeforeEach
    public void setup() {
        testPit = new BallPit();
        b1 = new Ball();
        b2 = new Ball(8, 2.0, 2.0, 0.2);
        testPit.addBall(b1);
        testPit.addBall(b2);
        b3 = new Ball();
    }

    @Test
    public void testBallPit() {
        assertEquals(testPit.getBalls().size(), 2);
    }

    @Test
    public void testGetBalls() {
        assertEquals(testPit.getBalls().get(0), b1);
        assertEquals(testPit.getBalls().get(1), b2);
        assertEquals(testPit.getBalls().size(), 2);
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
    public void testNextState() {

    }



    @Test
    public void testMoveBalls() {
        testPit.nextState();
        assertEquals(6.0, testPit.getBalls().get(0).getPosX());
        assertEquals(4.0, testPit.getBalls().get(0).getPosY());
        assertEquals(-9.81*BallPit.tickRate, testPit.getBalls().get(0).getSpeedY(), 0.01);
        assertEquals(0, testPit.getBalls().get(0).getSpeedX());

        assertEquals(2.0, testPit.getBalls().get(1).getPosX());
        assertEquals(2.0, testPit.getBalls().get(1).getPosY());
        assertEquals(-9.81*BallPit.tickRate, testPit.getBalls().get(1).getSpeedY(), 0.01);
        assertEquals(0, testPit.getBalls().get(1).getSpeedX());
    }

    @Test
    public void testHandleWallsBottomLeft() {
        testPit.clearBallPit();
        Ball b4 = new Ball(20, 0.0, 0.0, 1);
        testPit.addBall(b4);
        testPit.addBall(b2);
        testPit.nextState();

        assertEquals(b4.getRadius(), b4.getPosY());
        assertEquals(b4.getRadius(), b4.getPosX());
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

        assertEquals(BallPit.HEIGHT - b5.getRadius(), b5.getPosY());
        assertEquals(BallPit.WIDTH - b5.getRadius(), b5.getPosX());
        assertEquals(2.0, b2.getPosY());
        assertEquals(2.0, b2.getPosX());
    }

}
