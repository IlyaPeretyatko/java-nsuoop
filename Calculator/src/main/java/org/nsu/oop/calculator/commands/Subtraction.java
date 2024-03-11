package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Subtraction implements Command {

    private List<String> args;

    @Override
    public void validateArgs(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    public void runCommand(ExecutionContext currentContext) {
        double a = currentContext.popValue();
        double b = currentContext.popValue();
        currentContext.pushValue(b - a);
    }
}
