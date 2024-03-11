package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;
import java.lang.Math;

public class Sqrt implements Command {

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
        double value = currentContext.popValue();
        currentContext.pushValue(Math.sqrt(value));
    }
}
