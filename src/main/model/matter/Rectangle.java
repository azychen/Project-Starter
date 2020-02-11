//package model.matter;
//
//import model.matter.Solid;
//
//import java.awt.*;
//
//public class Rectangle extends Solid {
//
//    protected static final double defaultWidth = 1.0;
//    protected static final double defaultHeight = 1.0;
//
//    protected double width;
//    protected double height;
//
//    public Rectangle() {
//        super();
//        shape = "Rectangle";
//        height = defaultHeight;
//        width = defaultWidth;
//    }
//
//    public Rectangle(double m, Color c, int x, int y, double w, double h) {
//        super(m, c, x, y);
//        shape = "Rectangle";
//        height = h;
//        width = w;
//    }
//
//
//    // GETTERS
//
//
//    // EFFECTS: returns the width of the rectangle
//    public double getWidth() {
//        return width;
//    }
//
//
//    // EFFECTS: returns the height of the rectangle
//    public double getHeight() {
//        return height;
//    }
//
//    // EFFECTS: returns the volume of the rectangle
//    public double getVolume() {
//        return height * width * depth;
//    }
//
//
//    /*
//     * PHYSICS QUANTITIES
//     */
//
//    public boolean collision(Solid s) {
//
//    }
//
//}
