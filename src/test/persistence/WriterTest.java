package persistence;

import model.BallPit;
import model.ImpossibleValueException;
import model.matter.Ball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testBallPits1.txt";
    private Writer testWriter;

    private BallPit testBallPit1;
    private BallPit testBallPit2;

    @BeforeEach
    void setup() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        List<Ball> balls1 = new ArrayList<>();
        List<Ball> balls2 = new ArrayList<>();

        try {
            for (int i = 0; i < 5; ++i) {
                Ball newBall = new Ball(i + 1, i + 1);
                balls1.add(newBall);
            }
            for (int i = 0; i < 20; ++i) {
                Ball newBall = new Ball(i + 1, i + 1);
                balls2.add(newBall);
            }
        } catch (ImpossibleValueException e) {
            fail();
            e.printStackTrace();
        }
        testBallPit1 = new BallPit("Mae", balls1);
        testBallPit2 = new BallPit("Jo", balls2);
    }

    @Test
    void testWriteAccounts() {
        testWriter.write(testBallPit1);
        testWriter.write(testBallPit2);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<BallPit> pits = Reader.readBallPits(new File(TEST_FILE));

            BallPit pit1 = pits.get(0);
            assertEquals("Mae", pit1.getName());
            assertEquals(5, pit1.getBalls().size());
            for (int i = 0; i < pit1.getBalls().size(); ++i) {
                Ball ball = pit1.getBalls().get(i);
                assertEquals(i + 1, ball.getRadius());
                assertEquals(i + 1, ball.getMass());

            }

            BallPit pit2 = pits.get(1);
            assertEquals("Jo", pit2.getName());
            assertEquals(20, pit2.getBalls().size());
            for (int i = 0; i < pit2.getBalls().size(); ++i) {
                Ball ball = pit2.getBalls().get(i);
                assertEquals(i + 1, ball.getRadius());
                assertEquals(i + 1, ball.getMass());
            }

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}