package org.nsu.oop.calculator.runtime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public void run() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/java/org/nsu/oop/calculator/runtime/instructions.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileParser fileParser = new FileParser(reader);
        fileParser.parse();
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
