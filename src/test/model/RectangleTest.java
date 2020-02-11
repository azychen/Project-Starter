//package model;
//
//import model.matter.Matter;
//import model.matter.Rectangle;
//import org.junit.jupiter.api.Assertions.*;
//import model.matter.Circle;
//import model.matter.Solid;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.awt.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RectangleTest {
//
//    Rectangle s1, s2;
//
//    @BeforeEach
//    void setup() {
//        s1 = new Rectangle();
//        s2 = new Rectangle(10, Color.GREEN, 400, 500, 3, 2);
//    }
//
//    @Test
//    void testGetIndex() {
//        assertEquals(1, s1.getIndex());
//        assertEquals(2, s2.getIndex());
//    }
//
//    @Test
//    void testRectangleDefault() {
//        assertEquals(Solid.defaultMass, s1.getMass());
//        assertEquals(Matter.defaultColor, s1.getColor());
//        assertEquals(0, s1.getSpeedX());
//        assertEquals(0, s1.getSpeedY());
//        assertEquals(Sandbox.WIDTH/2, s1.getPosX());
//        assertEquals(Sandbox.HEIGHT/2, s1.getPosY());
//    }
//
//    @Test
//    void testRectangleUserSpecified() {
//        assertEquals(10, s2.getMass());
//        assertEquals(Color.GREEN, s2.getColor());
//        assertEquals(0, s2.getSpeedX());
//        assertEquals(0, s2.getSpeedY());
//        assertEquals(400, s2.getPosX());
//        assertEquals(500, s2.getPosY());
//    }
//
//    @Test
//    void testGetWidth() {
//        assertEquals(1, s1.getWidth());
//        assertEquals(3, s2.getWidth());
//    }
//
//    @Test
//    void testGetHeight() {
//        assertEquals(1, s1.getHeight());
//        assertEquals(2, s2.getHeight());
//    }
//
//    @Test
//    void testGetMass() {
//        assertEquals(5, s1.getMass());
//        assertEquals(10, s2.getMass());
//    }
//
//    @Test
//    void testGetVolume() {
//        assertEquals(1, s1.getVolume(), 0.01);
//        assertEquals(6, s2.getVolume(), 0.01);
//    }
//
//    @Test
//    void testGetDensity() {
//        assertEquals(5 / 1.0, s1.getDensity(), 0.01);
//        assertEquals(10 / 6.0, s2.getDensity(), 0.01);
//    }
//
//    @Test
//    void testIsSubmergedIsNot() {
//        assertFalse(s1.isSubmerged());
//        assertFalse(s2.isSubmerged());
//    }
//
//    @Test
//    void testIsSubmergedIsSubmerged() {
//        s1.setFractionSubmerged(0.25);
//        assertTrue(s1.isSubmerged());
//    }
//
//    @Test
//    void testAddSpeedOnlyX() {
//        s1.addSpeed(10, 0);
//        assertEquals(s1.getSpeedX(), 10);
//        assertEquals(s1.getSpeedY(), 0);
//    }
//
//    @Test
//    void testAddSpeedOnlyY() {
//        s1.addSpeed(0, -30);
//        assertEquals(s1.getSpeedX(), 0);
//        assertEquals(s1.getSpeedY(), -30);
//    }
//
//    @Test
//    void testAddSpeedBothXAndYAndMultipleAdds() {
//        s1.addSpeed(10, 0);
//        s1.addSpeed(13, -40);
//        assertEquals(23, s1.getSpeedX());
//        assertEquals(-40, s1.getSpeedY());
//    }
//
//
//
//}
//
