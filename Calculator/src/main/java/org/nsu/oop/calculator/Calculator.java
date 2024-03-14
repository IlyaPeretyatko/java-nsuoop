package org.nsu.oop.calculator;


import org.nsu.oop.calculator.exception.stream.ReaderNotCreatedException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Calculator {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private final ExecutionContext currentContext;

    public Calculator() {
        currentContext = new ExecutionContext();
        log.info("Initialization Calculator");
    }

    public void run(String path) {
        if (path.isEmpty()) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
                log.info("Open input stream for reading.");
                InstructionParser consoleParser= new InstructionParser();
                consoleParser.parse(currentContext, inputStreamReader);
            } catch (IOException e) {
                log.warning("ReaderNotCreatedException.");
                throw new ReaderNotCreatedException();
            }
        } else {
            try (FileReader fileReader = new FileReader(path)) {
                log.info("Open file for reading.");
                InstructionParser fileParser = new InstructionParser();
                fileParser.parse(currentContext, fileReader);
            } catch (IOException e) {
                log.warning("ReaderNotCreatedException.");
                throw new ReaderNotCreatedException();
            }
        }
    }
}
