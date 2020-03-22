package ui;

import model.BallPit;
import model.matter.Ball;
import persistence.Writer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


public class PitPanel extends JPanel {

    private Main main;

    private BallPit pit;

    private static final int WIDTH = BallPit.toPixels(BallPit.WIDTH);
    private static final int HEIGHT = BallPit.toPixels(BallPit.HEIGHT);

    private static JLabel pitName;

    private static final String OVERLAY_FILE = "./data/overlay.png";
    protected static final String SAVES_FILE = "./data/ballpits.txt";


    private static final Color backgroundColor = Color.LIGHT_GRAY;
    private static final Font font = new Font("Helvetica Neue", Font.BOLD, 30);

    private MouseListener ml;

    // EFFECTS: constructs panel illustrating ball pit
    public PitPanel(BallPit pit, Main main) {
        this.pit = pit;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(backgroundColor);
        this.main = main;
        initPitName();
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
            handleSettingsInput(input);
        }
    }

    // REQUIRES: input != null
    // MODIFIES: this
    // EFFECTS: handles user input for settings menu
    private void handleSettingsInput(String input) {
        switch (input) {
            case "Add Ball":
                handleAddBall();
                break;
            case "Clear Balls":
                handleClearPit();
                break;
            case "Rename Ball Pit":
                handleRenamePit();
                break;
            case "Save Ball Pit":
                handleSavePit();
                break;
            case "Quit to Main Menu":
                handleQuitToMain();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles add ball
    public void handleAddBall() {
        double mass = makeSliderPane("mass (g)");
        double radius = makeSliderPane("radius (cm)");
        Ball b = new Ball(mass * 0.01, radius * 0.01);
        pit.addBall(b);
    }

    // MODIFIES: this
    // EFFECTS: creates pane with given question
    public double makeSliderPane(String property) {
        JOptionPane addPane = new JOptionPane();
        JSlider slider = makeSlider(addPane);
        addPane.setMessage(new Object[] { "Set " + property + " > 0", slider });
        addPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        addPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = addPane.createDialog(main, "Add Ball");
        dialog.setVisible(true);
        String test = "test";
        if (addPane.getInputValue().getClass() != test.getClass()) {
            return (int) addPane.getInputValue();
        } else {
            return makeSliderPane(property);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles clear ball pit
    public void handleClearPit() {
        pit.clearBallPit();
        JOptionPane.showMessageDialog(null,
                pit.getName() + " was successfully cleared!",
                pit.getName() + " Cleared",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: handles rename ball pit
    public void handleRenamePit() {
        String oldName = pit.getName();
        String newName = (String)JOptionPane.showInputDialog(
                null,
                "Enter new name:",
                "Settings",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "My Ball Pit");
        pit.setName(newName);
        pitName.setText(newName);
        JOptionPane.showMessageDialog(null,
                oldName + " was renamed to " + pit.getName() + ".",
                "Success!",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: handles save ball pit
    public void handleSavePit() {
        try {
            savePit();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "File not found. Please try again.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(null,
                    "Unsupported encoding. Please try again.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to save ball pit
    private void savePit() throws FileNotFoundException, UnsupportedEncodingException {
        JOptionPane.showMessageDialog(null,
                "Attempting to save " + pit.getName() + "...",
                "Saving...",
                JOptionPane.PLAIN_MESSAGE);
        Writer writer = new Writer(new File(SAVES_FILE));
        writer.write(pit);
        writer.close();
        JOptionPane.showMessageDialog(null,
                "Successfully saved " + pit.getName() + "!",
                "Success!",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: handles quit to main menu
    public void handleQuitToMain() {
        Object[] options = {"Yes",
                "No"};
        int res = JOptionPane.showOptionDialog(null,
                "Would you like to save " + pit.getName() + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (res == JOptionPane.YES_OPTION) {
            handleSavePit();
        }
//        setPit(new BallPit(""));
        main.showMainOptionPane();
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



    static JSlider makeSlider(final JOptionPane optionPane) {
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(0);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setInputValue(theSlider.getValue());
                }
            }
        };
        slider.addChangeListener(changeListener);
        return slider;
    }


}

