package ui;

import model.BallPit;
import model.matter.Ball;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;


// represents the panel which the ball pit is rendered on.

public class PitPanel extends JPanel {

    private Main main;
    private PitPanelEventHandler pph;

    private BallPit pit;

    private static final int WIDTH = BallPit.toPixels(BallPit.WIDTH);
    private static final int HEIGHT = BallPit.toPixels(BallPit.HEIGHT);

    private static JLabel pitName;

    private static final String OVERLAY_FILE = "./data/overlay.png";

    private static final Color backgroundColor = Color.LIGHT_GRAY;
    private static final Font font = new Font("Helvetica Neue", Font.BOLD, 30);

    // EFFECTS: constructs panel illustrating ball pit
    public PitPanel(BallPit pit, Main main) {
        this.pit = pit;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(backgroundColor);
        this.main = main;
        initPitName();
        pph = new PitPanelEventHandler(this);
    }

    // MODIFIES: this
    // EFFECTS: sets pit
    public void setPit(BallPit pit) {
        this.pit = pit;
        pitName.setText(pit.getName());
    }

    // EFFECTS: returns pit
    public BallPit getPit() {
        return pit;
    }

    // EFFECTS: returns main
    public Main getMain() {
        return main;
    }

    // EFFECTS: returns pitName
    public JLabel getPitName() {
        return pitName;
    }

    // EFFECTS: returns the event handler
    public PitPanelEventHandler getEventHandler() {
        return pph;
    }


    // MODIFIES: this
    // EFFECTS: initializes size/color of panel, updates with ball pit
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBalls(g);
        g.setFont(font);

    }

    // MODIFIES: this
    // EFFECTS: instantiates the ball pit name
    private void initPitName() {
        pitName = new JLabel(pit.getName());
        pitName.setHorizontalAlignment(SwingConstants.LEFT);
        pitName.setVerticalAlignment(SwingConstants.TOP);
        pitName.setFont(font);
        add(pitName);
    }

    // MODIFIES: this
    // EFFECTS: handles mouse click for settings
    public void handleMouseClick(MouseEvent e) {
        Object[] options = {"Add Ball", "Clear Balls", "Rename Ball Pit", "Save Ball Pit", "Quit to Main Menu"};
        String input = (String)JOptionPane.showInputDialog(
                null,
                "What would you like to do?",
                "Settings",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                "Select input...");
        if (input != null) {
            pph.handleSettingsInput(input);
        }
    }



    // MODIFIES: g
    // EFFECTS: draws balls
    private void drawBalls(Graphics g) {
        for (Ball b : pit.getBalls()) {
            drawBall(g, b);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the ball
    private void drawBall(Graphics g, Ball b) {
        Color savedColor = g.getColor();
        g.setColor(b.getColor());
        g.fillOval(
                BallPit.toPixels(b.getPosX() - b.getRadius()),
                HEIGHT - BallPit.toPixels(b.getPosY() + b.getRadius()),
                BallPit.toPixels(b.getRadius()) * 2,
                BallPit.toPixels(b.getRadius()) * 2);
        g.setColor(savedColor);
//        g.drawImage(ballOverlay,
//                BallPit.toPixels(b.getPosX() - b.getRadius()),
//                HEIGHT - BallPit.toPixels(b.getPosY() + b.getRadius()),
//                BallPit.toPixels(b.getRadius()) * 2,
//                BallPit.toPixels(b.getRadius()) * 2,
//                null);
    }



    public static JSlider makeSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(0);
        ChangeListener changeListener = changeEvent -> {
            JSlider theSlider = (JSlider) changeEvent.getSource();
            if (!theSlider.getValueIsAdjusting()) {
                optionPane.setInputValue(theSlider.getValue());
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }


}

