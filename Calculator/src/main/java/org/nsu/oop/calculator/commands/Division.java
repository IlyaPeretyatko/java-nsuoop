package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Division implements Command {
    ExecutionContext currentContext;

    @Override
    public void initial(List<String> args, ExecutionContext context) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        currentContext = context;
    }


    public void runCommand() {
        double a = currentContext.popValue();
        double b = currentContext.popValue();
        currentContext.pushValue(b / a);
    }
}
