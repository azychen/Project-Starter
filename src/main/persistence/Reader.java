package persistence;

import model.BallPit;
import model.matter.Ball;
import model.ImpossibleValueException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *      A class to read saved ball pits from a .txt file.
 *
 *      SOURCE: from TellerApp
 */


public class Reader {

    public static final String DELIMITER = ",";

    // EFFECTS: returns list of BallPit parsed from file, throws
    //          IOException if unsuccessful
    public static List<BallPit> readBallPits(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns file contents as a list of strings,
    //          each string is one line in file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns list of BallPit parsed from list of strings
    //                  each string contains data for one BallPit
    private static List<BallPit> parseContent(List<String> fileContent) {
        List<BallPit> pits = new ArrayList<>();
        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            pits.add(parseBallPit(lineComponents));
        }
        return pits;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }


    // REQUIRES: components have at least size 1 where:
    //           components.size() % 7 == 1;
    //           element 0: represents name of ball pit
    //           elements 1 - 7, 8 - 14, etc...:
    //                  represents 1 ball per 7
    // EFFECTS: returns an BallPit constructed from components
    private static BallPit parseBallPit(List<String> components) {
        List<Ball> balls = new ArrayList<>();
        String name = components.get(0);
        if (components.size() < 2) {
            return new BallPit(name);
        }
        for (int i = 1; i < components.size(); i += 7) {
            double m = Double.parseDouble(components.get(i));
            double x = Double.parseDouble(components.get(i + 1));
            double y = Double.parseDouble(components.get(i + 2));
            double r = Double.parseDouble(components.get(i + 3));
            double dx = Double.parseDouble(components.get(i + 4));
            double dy = Double.parseDouble(components.get(i + 5));
            int index = Integer.parseInt(components.get(i + 6));
            try {
                Ball newBall = new Ball(m, x, y, r, dx, dy, index);
                balls.add(newBall);
            } catch (ImpossibleValueException e) {
                e.printStackTrace();
            }
        }
        return new BallPit(name, balls);
    }


}
