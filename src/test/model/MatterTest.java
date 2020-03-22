package model;

import model.matter.Ball;
import model.matter.Matter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;


public class MatterTest {

    Matter m;

    @BeforeEach
    void setup() {
        m = new Ball(1, 1, 1, 1, 1, 1, Color.RED);
    }

    @Test
    void testConstructor() {
        assertEquals(Color.RED, m.getColor());
    }

}
