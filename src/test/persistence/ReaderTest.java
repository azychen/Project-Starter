package persistence;

import model.BallPit;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 *      This class tests the Reader class.
 */
public class ReaderTest {

/*
testBallPit1, 10, 5.0, 4.5, 0.25, 3.25, -1.33, 1
testBallPit2, 1, 2, 3, 4, 5, 6, 7
 */

    @Test
    public void testParseBallPitFile1() {
        try {
            List<BallPit> ballPits = Reader.readBallPits(new File("./data/testBallPits1.txt"));

            BallPit testBallPit1 = ballPits.get(0);
            assertEquals("testBallPit1", testBallPit1.getName());
            assertEquals(10, testBallPit1.getBalls().get(0).getMass());
            assertEquals(5.0, testBallPit1.getBalls().get(0).getPosX());
            assertEquals(4.5, testBallPit1.getBalls().get(0).getPosY());
            assertEquals(0.25, testBallPit1.getBalls().get(0).getRadius());
            assertEquals(3.25, testBallPit1.getBalls().get(0).getSpeedX());
            assertEquals(-1.33, testBallPit1.getBalls().get(0).getSpeedY());
            assertEquals(1, testBallPit1.getBalls().get(0).getIndex());

            BallPit testBallPit2 = ballPits.get(1);
            assertEquals("testBallPit2", testBallPit2.getName());
            assertEquals(1, testBallPit2.getBalls().get(0).getMass());
            assertEquals(2, testBallPit2.getBalls().get(0).getPosX());
            assertEquals(3, testBallPit2.getBalls().get(0).getPosY());
            assertEquals(4, testBallPit2.getBalls().get(0).getRadius());
            assertEquals(5, testBallPit2.getBalls().get(0).getSpeedX());
            assertEquals(6, testBallPit2.getBalls().get(0).getSpeedY());
            assertEquals(7, testBallPit2.getBalls().get(0).getIndex());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    /*
     * testBallPit3, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7
     */

    @Test
    public void testParseBallPitFile2() {
        try {
            List<BallPit> ballPits = Reader.readBallPits(new File("./data/testBallPits2.txt"));

            BallPit testBallPit3 = ballPits.get(0);
            assertEquals("testBallPit3", testBallPit3.getName());

            assertEquals(1.1, testBallPit3.getBalls().get(0).getMass());
            assertEquals(1.2, testBallPit3.getBalls().get(0).getPosX());
            assertEquals(1.3, testBallPit3.getBalls().get(0).getPosY());
            assertEquals(1.4, testBallPit3.getBalls().get(0).getRadius());
            assertEquals(1.5, testBallPit3.getBalls().get(0).getSpeedX());
            assertEquals(1.6, testBallPit3.getBalls().get(0).getSpeedY());
            assertEquals(1, testBallPit3.getBalls().get(0).getIndex());

            assertEquals(2.1, testBallPit3.getBalls().get(1).getMass());
            assertEquals(2.2, testBallPit3.getBalls().get(1).getPosX());
            assertEquals(2.3, testBallPit3.getBalls().get(1).getPosY());
            assertEquals(2.4, testBallPit3.getBalls().get(1).getRadius());
            assertEquals(2.5, testBallPit3.getBalls().get(1).getSpeedX());
            assertEquals(2.6, testBallPit3.getBalls().get(1).getSpeedY());
            assertEquals(2, testBallPit3.getBalls().get(1).getIndex());

            assertEquals(3.1, testBallPit3.getBalls().get(2).getMass());
            assertEquals(3.2, testBallPit3.getBalls().get(2).getPosX());
            assertEquals(3.3, testBallPit3.getBalls().get(2).getPosY());
            assertEquals(3.4, testBallPit3.getBalls().get(2).getRadius());
            assertEquals(3.5, testBallPit3.getBalls().get(2).getSpeedX());
            assertEquals(3.6, testBallPit3.getBalls().get(2).getSpeedY());
            assertEquals(3, testBallPit3.getBalls().get(2).getIndex());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    public void testIOException() {
        try {
            Reader.readBallPits(new File("./path/yah/yeet/dab/onGod.txt"));
            fail();
        } catch (IOException e) {
            // expected!
        }
    }
}
