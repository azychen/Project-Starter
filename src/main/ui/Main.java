package ui;


/*
 *      This program was built for a university assignment.
 */


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import model.BallPit;
import persistence.Reader;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    private PitPanel pp;

    // FILES
    private static final String SOUND_FILE = "./data/bounce.wav";
    private static final String BALLPITS_FILE = "./data/ballpits.txt";

    Timer timer;

    public static void main(String[] args) {
        new Main();
    }

    // EFFECTS: constructs main menu
    public Main() {
        super("THE BALL PIT");
        setTitle("THE BALL PIT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        centreOnScreen();
        setVisible(true);

        initPitPanel();
        showMainOptionPane();
    }

    // MODIFIES: this
    // EFFECTS: instantiates pit panel
    private void initPitPanel() {
        pp = new PitPanel(new BallPit(""),this);
        pp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pp.setLayout(new GridLayout());

        MainEventHandler mh = new MainEventHandler(this);

        addTimer();
        add(pp);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    public PitPanel getPitPanel() {
        return pp;
    }

    public void showMainOptionPane() {
        String[] options = {"Create new ball pit", "Load saved ball pit", "Quit"};
        int x = JOptionPane.showOptionDialog(null, "What would you like to do?",
                "Select option...",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        handleMainMenuInput(x);
    }

    // EFFECTS: handle main menu user input
    private void handleMainMenuInput(int x) {
        switch (x) {
            case 0:
                newPitOption();
                break;
            case 1:
                loadPitOption();
                break;
            case 2:
                quitOption();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new ball pit
    private void newPitOption() {
        String userPitName = (String)JOptionPane.showInputDialog(
                null,
                "Enter name of your ball pit...",
                "New Ball Pit",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "My Ball Pit");
        if ((userPitName != null) && (userPitName.length() > 0)) {
            createPit(userPitName);
        } else {
            showMainOptionPane();
        }
    }

    // REQUIRES: userPitName != null
    // MODIFIES: this
    // EFFECTS: creates pit
    private void createPit(String userPitName) {
        BallPit bp = new BallPit(userPitName);
        pp.setPit(bp);
        JOptionPane.showMessageDialog(null,
                userPitName + " was successfully created!",
                userPitName,
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: loads an existing ball pit from save
    private void loadPitOption() {
        try {
            java.util.List<BallPit> pits = Reader.readBallPits(new File(BALLPITS_FILE));
            if (pits.size() < 1) {
                displayNoSaves();
                return;
            }
            String input = displaySavedPits(pits);
            if (input != null) {
                findBallPit(input, pits);
            } else {
                showMainOptionPane();
            }
        } catch (IOException e) {
            System.out.println("That ball pit doesn't exist!\n");
        }
    }

    // REQUIRES: input != null
    // MODIFIES: this
    // EFFECTS: finds ball pit
    private void findBallPit(String input, java.util.List<BallPit> pits) {
        for (BallPit bp : pits) {
            if (input.equals(bp.getName())) {
                pp.setPit(bp);
                JOptionPane.showMessageDialog(null,
                        bp.getName() + " was successfully loaded!",
                        "Load Ball Pit...",
                        JOptionPane.PLAIN_MESSAGE);
                break;
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: notifies user that there are no saved ball pits
    private void displayNoSaves() {
        JOptionPane.showMessageDialog(null,
                "There are no saved ball pits! Why not make one?",
                "Warning",
                JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: displays saved ball pit in message
    private String displaySavedPits(java.util.List<BallPit> pits) {
        Object[] saves = new String[pits.size()];
        for (int i = 0; i < pits.size(); ++i) {
            saves[i] = pits.get(i).getName();
        }
        return (String)JOptionPane.showInputDialog(
                null,
                "Select saved ball pit...",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                saves,
                "Select pit...");
    }

    // MODIFIES: this
    // EFFECTS: exits the program with exit message
    private void quitOption() {
        JOptionPane.showMessageDialog(null,
                "See you next time!",
                "Goodbye!",
                JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }


    // MODIFIES: this
    // EFFECTS: centers screen
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    // MODIFIES: this
    // EFFECTS: adds timer to frame
    private void addTimer() {
        timer = new Timer((int) (BallPit.TICK_RATE * 150),
                e -> {
                    pp.getPit().nextState();
                    pp.repaint();
                });
        timer.start();
    }


    // EFFECTS: plays collision sound
    public static void playCollisionSound() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(SOUND_FILE).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Audio exception thrown");
            e.printStackTrace();
        }
    }


}
