package model;

import model.matter.Ball;
import model.matter.Matter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

public class BallTest {

    Ball s1, s2;

    @BeforeEach
    void setup() {
        s1 = new Ball();
        s2 = new Ball(10, 4.0, 5.0, 10);
    }

    @Test
    void testGetIndex() {
        assertEquals(3, s1.getIndex());
        assertEquals(4, s2.getIndex());
    }

    @Test
    void testGetSpeed() {
        s1.setSpeedY(3.0);
        s1.setSpeedX(4.0);
        assertEquals(5.0, s1.getSpeed());
    }

    @Test
    void testBallDefault() {
        assertEquals(Ball.defaultMass, s1.getMass());
        assertEquals(0, s1.getSpeedX());
        assertEquals(0, s1.getSpeedY());
        assertEquals(BallPit.WIDTH/2, s1.getPosX());
        assertEquals(BallPit.HEIGHT/2, s1.getPosY());
        assertEquals(Ball.PI * Matter.depth, s1.getVolume(), 0.01);
    }

    @Test
    void testBallUserSpecified() {
        assertEquals(10, s2.getMass());
        assertEquals(0, s2.getSpeedX());
        assertEquals(0, s2.getSpeedY());
        assertEquals(4.0, s2.getPosX());
        assertEquals(5.0, s2.getPosY());
        assertEquals(Ball.PI * 10 * 10 * Matter.depth, s2.getVolume(), 0.01);
    }
    @Test
    void testBallSlightlyMoreOptions() {
        s1 = new Ball(Ball.defaultMass, 1);
        assertEquals(Ball.defaultMass, s1.getMass());
        assertEquals(0, s1.getSpeedX());
        assertEquals(0, s1.getSpeedY());
        assertEquals(BallPit.WIDTH/2, s1.getPosX());
        assertEquals(BallPit.HEIGHT/2, s1.getPosY());
        assertEquals(Ball.PI * Matter.depth, s1.getVolume(), 0.01);
    }



    @Test
    public void testSetPosX() {
        s2.setPosX(1.2);
        assertEquals(1.2, s2.getPosX());
    }

    @Test
    public void testSetPosY() {
        s2.setPosY(3.4);
        assertEquals(3.4, s2.getPosY());
    }

    @Test
    void testGetRadius() {
        assertEquals(1, s1.getRadius());
        assertEquals(10, s2.getRadius());
    }

    @Test
    void testGetMass() {
        assertEquals(5, s1.getMass());
        assertEquals(10, s2.getMass());
    }

    @Test
    void testGetVolume() {
        assertEquals(Ball.PI * Matter.depth, s1.getVolume(), 0.01);
        assertEquals(Ball.PI * 10 * 10 * Matter.depth, s2.getVolume(), 0.01);
    }

    @Test
    void testGetDensity() {
        assertEquals(5 / (Ball.PI * Matter.depth), s1.getDensity(), 0.01);
        assertEquals(10 / (Ball.PI * 10 * 10 * Matter.depth), s2.getDensity(), 0.01);
    }

    @Test
    void testSetSpeedX() {
        s1.setSpeedX(10);
        assertEquals(s1.getSpeedX(), 10);
        assertEquals(s1.getSpeedY(), 0);
    }

    @Test
    void testSetSpeedY() {
        s1.setSpeedY(-30);
        assertEquals(s1.getSpeedX(), 0);
        assertEquals(s1.getSpeedY(), -30);
    }

    @Test
    void testCollisionNoCollision() {
        Ball s5 = new Ball(20, 5, 5, 0.5);
        Ball s6 = new Ball(5, 1, 1, 0.2);
        s5.collision(s6);
        assertFalse(s5.collision(s6));
    }

    @Test
    void testCollisionButThereIsACollision() {
        Ball s5 = new Ball(20, 5, 5, 0.5);
        Ball s6 = new Ball(5, 5, 5, 0.2);
        assertTrue(s5.collision(s6));
    }

    @Test
    void testBounceBothOnSameXPos() {
        Ball s7 = new Ball(5, 3, 3, 0.5);
        Ball s8 = new Ball(2, 3, 2, 0.5);
        s7.setSpeedY(-5);
        s8.setSpeedY(2);
        s8.setSpeedX(3);
        s7.bounce(s8);
        assertEquals(-0.95, s7.getSpeedY(), 0.01);
        assertEquals(-8 * Ball.conserved, s8.getSpeedY(), 0.01);
        assertEquals(3 * Ball.conserved, s8.getSpeedX());
    }

    @Test
    void testBounceBothOnSameYPos() {
        Ball s9 = new Ball(2, 2, 1, 0.25);
        Ball s10 = new Ball(3, 3, 1, 0.75);
        s9.setSpeedX(6);
        s10.setSpeedX(-3);
        s9.setSpeedY(-1);
        s9.bounce(s10);
        assertEquals(-4.8 * Ball.conserved, s9.getSpeedX());
        assertEquals(-1 * Ball.conserved, s9.getSpeedY());
        assertEquals(4.2 * Ball.conserved, s10.getSpeedX());

    }

    @Test
    void testBounceInBothDimensionsThisLeftOfOther() {
        Ball s11 = new Ball(5, 2, 2, sqrt(3) / 2);
        Ball s12 = new Ball(3, 2 + sqrt(3), 3, sqrt(3) / 2);
        s11.setSpeedX(2);
        s11.setSpeedY(3);
        s12.setSpeedX(-1);
        s12.setSpeedY(-3);
        s11.bounce(s12);
        assertEquals(-2.692855, s11.getSpeedX(), 0.01);
        assertEquals(-1.554720, s11.getSpeedY(), 0.01);
        assertEquals(3.057547, s12.getSpeedX(), 0.01);
        assertEquals(1.762755, s12.getSpeedY(), 0.01);
    }

    @Test
    void testBounceInBothDimensionsThisRightOfOther() {
        Ball s11 = new Ball(5, 2, 2, sqrt(3) / 2);
        Ball s12 = new Ball(3, 2 + sqrt(3), 3, sqrt(3) / 2);
        s11.setSpeedX(2);
        s11.setSpeedY(3);
        s12.setSpeedX(-1);
        s12.setSpeedY(-3);
        s12.bounce(s11);
        assertEquals(-2.692855, s11.getSpeedX(), 0.01);
        assertEquals(-1.554720, s11.getSpeedY(), 0.01);
        assertEquals(3.057547, s12.getSpeedX(), 0.01);
        assertEquals(1.762755, s12.getSpeedY(), 0.01);
    }



}

