package org.nsu.oop.calculator;


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Calculator {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private final String pathToInstruction;

    public Calculator(String path) {
        log.info("Initialization Calculator");
        pathToInstruction = path;
    }

    public void run() {
        if (pathToInstruction.isEmpty()) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
                FileParser consoleParser= new FileParser();
                consoleParser.parse(inputStreamReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (FileReader fileReader = new FileReader(pathToInstruction)) {
                log.info("Open file for reading.");
                FileParser fileParser = new FileParser();
                fileParser.parse(fileReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
