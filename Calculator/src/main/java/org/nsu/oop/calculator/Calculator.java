package org.nsu.oop.calculator;


import org.nsu.oop.calculator.exception.stream.ReaderNotCreatedException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Calculator {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    protected final ExecutionContext currentContext;

    public Calculator() {
        log.info("Initialization Calculator");
        currentContext = new ExecutionContext();
    }

    public void run(String path) {
        if (path.isEmpty()) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
                InstructionParser consoleParser= new InstructionParser();
                consoleParser.parse(inputStreamReader);
            } catch (IOException e) {
                throw new ReaderNotCreatedException();
            }
        } else {
            try (FileReader fileReader = new FileReader(path)) {
                log.info("Open file for reading.");
                InstructionParser fileParser = new InstructionParser();
                fileParser.parse(fileReader);
            } catch (IOException e) {
                throw new ReaderNotCreatedException();
            }
        }
    }
}
