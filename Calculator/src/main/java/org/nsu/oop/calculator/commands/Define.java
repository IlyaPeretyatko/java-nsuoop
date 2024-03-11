package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Define implements Command {

    private List<String> args;


    @Override
    public void validateArgs(List<String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException();
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }


    public void runCommand(ExecutionContext currentContext, String variable, Double value) {
        currentContext.pushVariable(variable, value);
    }
}