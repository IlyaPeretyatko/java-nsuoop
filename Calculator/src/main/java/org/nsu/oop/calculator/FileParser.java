package org.nsu.oop.calculator;

import org.nsu.oop.calculator.commands.Creator;
import org.nsu.oop.calculator.commands.Command;

import java.io.BufferedReader;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;



public class FileParser {
    private String line;
    private String commandName;
    private List<String> args;
    private final ExecutionContext currentContext;


    public FileParser() {
        currentContext = new ExecutionContext();
    }

    public void parse(FileReader reader) {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    parseLine();
                    Creator commandCreator = new Creator();
                    Command command = commandCreator.create(commandName);
                    command.initial(args);
                    command.runCommand(currentContext);
                    args.clear();
                }
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseLine() {
        String[] lineSplit = line.split(" ");
        commandName = lineSplit[0];
        args = new ArrayList<>(Arrays.asList(lineSplit));
        args.remove(commandName);
    }
}

