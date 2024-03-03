package org.nsu.oop.calculator;

import org.nsu.oop.calculator.commands.Creator;
import org.nsu.oop.calculator.commands.Command;

import java.io.BufferedReader;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Logger;


public class FileParser {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private String line;
    private String commandName;
    private List<String> args;
    private final ExecutionContext currentContext;


    public FileParser() {
        currentContext = new ExecutionContext();
        log.info("Initialization FileParser.");
    }

    public void parse(FileReader reader) {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            Creator commandCreator = new Creator();
            do {
                log.info("Read line.");
                line = bufferedReader.readLine();
                if (line != null) {
                    parseLine();
                    Command command = commandCreator.create(commandName);
                    log.info("Initialization command.");
                    command.initial(args);
                    log.info("Run command.");
                    command.runCommand(currentContext);
                    args.clear();
                }
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseLine() {
        log.info("Parse line.");
        String[] lineSplit = line.split(" ");
        commandName = lineSplit[0];
        args = new ArrayList<>(Arrays.asList(lineSplit));
        args.remove(commandName);
    }
}

