package ui;

import model.Sandbox;
import model.matter.Circle;
import model.matter.Rectangle;
import model.matter.Solid;

import java.awt.*;

public class PhysicsSandbox {
    public static void main(String[] args) {
        Sandbox sandbox = new Sandbox();
        SandboxWindow window = new SandboxWindow(sandbox);

        Solid solidRect = new Rectangle();
        Solid solidCirc = new Circle(50, Color.BLUE, 100, 100, 25);
        sandbox.addSolid(solidRect);
        sandbox.addSolid(solidCirc);

        window.printMatter();

        sandbox.moveMatter();
    }
}
