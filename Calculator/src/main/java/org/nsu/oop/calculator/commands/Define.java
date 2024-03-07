package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Define implements Command {

    ExecutionContext currentContext;


    @Override
    public void initial(List<String> args, ExecutionContext context) {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }
        currentContext = context;
    }


    public void runCommand(String variable, Double value) {
        currentContext.pushVariable(variable, value);
    }
}