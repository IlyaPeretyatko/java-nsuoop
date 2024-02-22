package org.nsu.oop.calculator.runtime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    private BufferedReader reader = null;

    public void run() {
        try {
            reader = new BufferedReader(new FileReader("src/main/java/org/nsu/oop/calculator/runtime/instructions.txt"));
        } catch (IOException e) {
            //
        }
        FileParser fileParser = new FileParser(reader);
        fileParser.parse();
    }

}
