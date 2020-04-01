package ui;

// this class handles user actions while inside a ball pit

import model.ImpossibleValueException;
import model.matter.Ball;
import persistence.Writer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class PitPanelEventHandler {

    protected static final String SAVES_FILE = "./data/ballpits.txt";

    private PitPanel pp;

    public PitPanelEventHandler(PitPanel pp) {
        this.pp = pp;
    }


    // REQUIRES: input != null
    // MODIFIES: this
    // EFFECTS: handles user input for settings menu
    public void handleSettingsInput(String input) {
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
        try {
            Ball b = new Ball(mass * 0.01, radius * 0.01);
            pp.getPit().addBall(b);
        } catch (ImpossibleValueException e) {
            Ball b = new Ball();
            pp.getPit().addBall(b);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates pane with given question
    public double makeSliderPane(String property) {
        JOptionPane addPane = new JOptionPane();
        JSlider slider = PitPanel.makeSlider(addPane);
        addPane.setMessage(new Object[] { "Set " + property + " > 0", slider });
        addPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        addPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = addPane.createDialog(pp.getMain(), "Add Ball");
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
        pp.getPit().clearBallPit();
        JOptionPane.showMessageDialog(null,
                pp.getPit().getName() + " was successfully cleared!",
                pp.getPit().getName() + " Cleared",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: handles rename ball pit
    public void handleRenamePit() {
        String oldName = pp.getPit().getName();
        String newName = (String)JOptionPane.showInputDialog(
                null,
                "Enter new name:",
                "Settings",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "My Ball Pit");
        if (newName != null) {
            pp.getPit().setName(newName);
            pp.getPitName().setText(newName);
            JOptionPane.showMessageDialog(null,
                    oldName + " was renamed to " + pp.getPit().getName() + ".",
                    "Success!",
                    JOptionPane.PLAIN_MESSAGE);
        }
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
                "Attempting to save " + pp.getPit().getName() + "...",
                "Saving...",
                JOptionPane.PLAIN_MESSAGE);
        Writer writer = new Writer(new File(SAVES_FILE));
        writer.write(pp.getPit());
        writer.close();
        JOptionPane.showMessageDialog(null,
                "Successfully saved " + pp.getPit().getName() + "!",
                "Success!",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: handles quit to main menu
    public void handleQuitToMain() {
        Object[] options = {"Yes",
                "No"};
        int res = JOptionPane.showOptionDialog(null,
                "Would you like to save " + pp.getPit().getName() + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (res == JOptionPane.YES_OPTION) {
            handleSavePit();
        }
        pp.getMain().showMainOptionPane();
    }


}
