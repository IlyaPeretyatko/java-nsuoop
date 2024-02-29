package org.nsu.oop.calculator;


import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    private String pathToInstruction;

    public Calculator(String path) {
        pathToInstruction = path;
    }

    public void run() {
        try (FileReader fileReader = new FileReader(pathToInstruction)) {
            FileParser fileParser = new FileParser();
            fileParser.parse(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
