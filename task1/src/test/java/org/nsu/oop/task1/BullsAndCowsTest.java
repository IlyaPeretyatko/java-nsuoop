package org.nsu.oop.task1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BullsAndCowsTest {

    @Test
    public void testConstructor() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new BullsAndCows();
        String rightOut = "Число сгенерированно.\n";
        Assertions.assertEquals(rightOut, outContent.toString());
        System.setOut(System.out);
    }

}



