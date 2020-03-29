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
        try {
            s2 = new Ball(10, 4.0, 5.0, 10);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
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
        assertEquals(0.19634954084375, s1.getVolume(), 0.01);
    }

    @Test
    void testBallUserAllParametersSpecifiedValid() {
        try {
            Ball testBall = new Ball(7.6, 7.5, 7.4, 7.3, 7.2, 7.1, 7);
            assertEquals(7.6, testBall.getMass());
            assertEquals(7.5, testBall.getPosX());
            assertEquals(7.4, testBall.getPosY());
            assertEquals(7.3, testBall.getRadius());
            assertEquals(7.2, testBall.getSpeedX());
            assertEquals(7.1, testBall.getSpeedY());
            assertEquals(7, testBall.getIndex());
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testBallUserAllParametersSpecificInvalidMass() {
        try {
            Ball testBall = new Ball(-2, 7.5, 7.4, 7.3, 7.2, 7.1, 7);
            fail();
        } catch (ImpossibleValueException e) {
            // expected;
        }
    }

    @Test
    void testBallUserAllParametersSpecificInvalidRadius() {
        try {
            Ball testBall = new Ball(3, 2, 7.4, -2, 7.2, 7.1, 7);
            fail();
        } catch (ImpossibleValueException e) {
            // expected;
        }
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
        try {
            s1 = new Ball(Ball.defaultMass, 1);
            assertEquals(Ball.defaultMass, s1.getMass());
            assertEquals(0, s1.getSpeedX());
            assertEquals(0, s1.getSpeedY());
            assertEquals(Ball.PI * Matter.depth, s1.getVolume(), 0.01);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
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
        assertEquals(0.25, s1.getRadius());
        assertEquals(10, s2.getRadius());
    }

    @Test
    void testGetMass() {
        assertEquals(5, s1.getMass());
        assertEquals(10, s2.getMass());
    }

    @Test
    void testGetVolume() {
        assertEquals(0.19634954084375, s1.getVolume(), 0.01);
        assertEquals(Ball.PI * 10 * 10 * Matter.depth, s2.getVolume(), 0.01);
    }

    @Test
    void testGetDensity() {
        assertEquals(25.46479089543109, s1.getDensity(), 0.01);
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
        try {
            Ball s5 = new Ball(20, 5, 5, 0.5);
            Ball s6 = new Ball(5, 1, 1, 0.2);
            s5.collision(s6);
            assertFalse(s5.collision(s6));
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testCollisionButThereIsACollision() {
        try {
            Ball s5 = new Ball(20, 5, 5, 0.5);
            Ball s6 = new Ball(5, 5, 5, 0.2);
            assertTrue(s5.collision(s6));
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testBounceBothOnSameXPos() {
        try {
            Ball s7 = new Ball(5, 3, 3, 0.5);
            Ball s8 = new Ball(2, 3, 2, 0.5);
            s7.setSpeedY(-5);
            s8.setSpeedY(2);
            s8.setSpeedX(3);
            s7.bounce(s8);
            assertEquals(2.732061902315196, s7.getSpeedY(), 0.01);
            assertEquals(-3.63, s8.getSpeedY(), 0.01);
            assertEquals(0, s8.getSpeedX(), 0.01);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testBounceBothOnSameYPos() {
        try {
            Ball s9 = new Ball(2, 2, 1, 0.25);
            Ball s10 = new Ball(3, 3, 1, 0.75);
            s9.setSpeedX(6);
            s10.setSpeedX(-3);
            s9.setSpeedY(-1);
            s9.bounce(s10);
            assertEquals(-1.5492408710612318, s9.getSpeedX());
            assertEquals(0, s9.getSpeedY(), 0.01);
            assertEquals(3.55, s10.getSpeedX(), 0.01);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testBounceInBothDimensionsThisLeftOfOther() {
        try {
            Ball s11 = new Ball(5, 2, 2, sqrt(3) / 2);
            Ball s12 = new Ball(3, 2 + sqrt(3), 3, sqrt(3) / 2);
            s11.setSpeedX(2);
            s11.setSpeedY(3);
            s12.setSpeedX(-1);
            s12.setSpeedY(-3);
            s11.bounce(s12);
            assertEquals(-1.8424798213842304, s11.getSpeedX(), 0.01);
            assertEquals(-1.06375622063205, s11.getSpeedY(), 0.01);
            assertEquals(2.092005858876402, s12.getSpeedX(), 0.01);
            assertEquals(1.207820145768565, s12.getSpeedY(), 0.01);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testBounceInBothDimensionsThisRightOfOther() {
        try {
            Ball s11 = new Ball(5, 2, 2, sqrt(3) / 2);
            Ball s12 = new Ball(3, 2 + sqrt(3), 3, sqrt(3) / 2);
            s11.setSpeedX(2);
            s11.setSpeedY(3);
            s12.setSpeedX(-1);
            s12.setSpeedY(-3);
            s12.bounce(s11);
            assertEquals(-1.8424798213842304, s11.getSpeedX(), 0.01);
            assertEquals(-1.06375622063205, s11.getSpeedY(), 0.01);
            assertEquals(2.092005858876402, s12.getSpeedX(), 0.01);
            assertEquals(1.207820145768565, s12.getSpeedY(), 0.01);
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testInside() {
        try {
            Ball b1 = new Ball(1.0, 1.0, 1.0, 1.0);
            assertTrue(b1.inside(1.0, 1.0));
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testInsideNotInside() {
        try {
            Ball b1 = new Ball(1.0, 1.0, 1.0, 1.0);
            assertFalse(b1.inside(5.0, 5.0));
        } catch (ImpossibleValueException e) {
            e.printStackTrace();
        }
    }



}

