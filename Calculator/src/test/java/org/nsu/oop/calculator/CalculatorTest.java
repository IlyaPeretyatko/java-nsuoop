package org.nsu.oop.calculator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private ByteArrayOutputStream outContent;
    private PrintStream printStream;

    @BeforeEach
    public void setup() {
        outContent = new ByteArrayOutputStream();
        printStream = new PrintStream(outContent);
        System.setOut(printStream);
    }

    @AfterEach
    void tear(){
        printStream.close();
        try {
            outContent.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.setOut(System.out);
    }

    @Test
    public void test1() {
        Calculator calculator = new Calculator("src/test/java/tests/instructions1.txt");
        calculator.run();
        String rightOut = "30.0\r\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

    @Test
    public void test2() {
        Calculator calculator = new Calculator("src/test/java/tests/instructions2.txt");
        calculator.run();
        String rightOut = "2.0\r\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

}
