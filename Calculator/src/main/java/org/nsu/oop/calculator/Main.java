package org.nsu.oop.calculator;


import org.nsu.oop.calculator.exception.stream.BufferedReaderNotCreatedException;
import org.nsu.oop.calculator.exception.stream.ReaderNotCreatedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            log.info("Initialization InstructionParser.");
            List<String> arguments;
            String commandName;
            while (true) {
                arguments = instructionParser.parse(bufferedReader);
                if (arguments == null) {
                    break;
                }
                commandName = arguments.getFirst();
                if (commandName.equalsIgnoreCase("EXIT")) {
                    break;
                }
                arguments.remove(commandName);
                calculator.run(commandName, arguments);
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
    }


}
