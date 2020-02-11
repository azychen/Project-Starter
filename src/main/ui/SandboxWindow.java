package ui;

import model.*;
import model.matter.Matter;
import model.matter.Solid;

public class SandboxWindow {

    private Sandbox sandbox;

    public SandboxWindow() {
        sandbox = new Sandbox();
    }

    public SandboxWindow(Sandbox sandbox) {
        this.sandbox = sandbox;
    }

    // EFFECTS: prints information relating to the matter in the Sandbox
    public void printMatter() {
        for (Solid m : sandbox.getSolids()) {
            int i = 1;
            System.out.println("Position of Item #" + i + ", a(n) " + m.getState()
                    + " " + m.getShape() + ": ");
            System.out.println("at (" + m.getPosX() + ", " + m.getPosY() + ")");
            ++i;
        }
        if (sandbox.isThereLiquid()) {
            System.out.println("There is a liquid in the sandbox: ");
            System.out.println(sandbox.getLiquid().getName() + " at a level of "
                                + sandbox.getLiquid().getLevel());
        }
    }

}
