package org.nsu.oop.calculator.runtime;

import org.nsu.oop.calculator.commands.Creator;
import org.nsu.oop.calculator.commands.Command;

import java.io.BufferedReader;


import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;



public class FileParser {
    private final BufferedReader reader;
    private String line;
    private String commandName;
    private List<String> args;
    private final ExecutionContext currentContext;


    public FileParser(BufferedReader reader) {
        this.reader = reader;
        currentContext = new ExecutionContext();
    }

    public void parse() {
        try {
            do {
                line = reader.readLine();
                if (line != null) {
                    parseLine();
                    Creator commandCreator = new Creator();
                    Command command = commandCreator.create(commandName);
                    command.runCommand(args, currentContext);
                    args.clear();
                }
            } while (line != null);
        } catch (IOException e) {
            line = null;
        }
    }

    private void parseLine() {
        String[] lineSplit = line.split(" ");
        commandName = lineSplit[0];
        args = new ArrayList<>(Arrays.asList(lineSplit));
        args.remove(commandName);
    }
}

