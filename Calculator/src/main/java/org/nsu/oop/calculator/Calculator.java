package org.nsu.oop.calculator;


import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public void run() {
        try (FileReader fileReader = new FileReader("src/main/resources/instructions.txt")) {
            FileParser fileParser = new FileParser();
            fileParser.parse(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
