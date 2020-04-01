package ui;

import model.BallPit;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class MainEventHandler {
    private Main main;
    private MouseListener ml;

    public MainEventHandler(Main main) {
        this.main = main;
        this.main.addKeyListener(new KeyHandler());
        addMouseListener();
        this.main.getContentPane().addMouseListener(ml);
    }

    // EFFECTS: adds mouse listener for ball pit
    private void addMouseListener() {
        ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    handleMouseClick(e);
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    main.getPitPanel().handleMouseClick(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        main.addMouseListener(ml);
    }

    // MODIFIES: this
    // EFFECTS: handles mouse click
    private void handleMouseClick(MouseEvent e) {
        double x = BallPit.toMeters(e.getX());
        double y = BallPit.toMeters(BallPit.toPixels(BallPit.HEIGHT) - e.getY());
        main.getPitPanel().getPit().launch(x, y);
    }

    // MODIFIES: this
    // EFFECTS: handles key presses
    public void handleKeyPress(int keyCode) {
        if (!handleEarthquake(keyCode)) {
            handleSettings(keyCode);
        }
    }

    // class to handle key events
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            handleKeyPress(e.getKeyCode());
        }
    }

    // MODIFIES: this
    // EFFECTS: handles "earthquake" key binds
    private boolean handleEarthquake(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                main.getPitPanel().getPit().earthquakeLeft();
                return true;
            case KeyEvent.VK_RIGHT:
                main.getPitPanel().getPit().earthquakeRight();
                return true;
            case KeyEvent.VK_UP:
                main.getPitPanel().getPit().earthquakeUp();
                return true;
            case KeyEvent.VK_DOWN:
                main.getPitPanel().getPit().earthquakeDown();
                return true;
            default:
                return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles settings key binds
    private void handleSettings(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_C:
                main.getPitPanel().getEventHandler().handleClearPit();
                break;
            case KeyEvent.VK_Q:
                main.getPitPanel().getEventHandler().handleQuitToMain();
                break;
            case KeyEvent.VK_S:
                main.getPitPanel().getEventHandler().handleSavePit();
                break;
            case KeyEvent.VK_R:
                main.getPitPanel().getEventHandler().handleRenamePit();
                break;
            case KeyEvent.VK_A:
                main.getPitPanel().getEventHandler().handleAddBall();
                break;
            case KeyEvent.VK_SPACE:
                main.getPitPanel().getPit().addRandomBall();
        }
    }
}
