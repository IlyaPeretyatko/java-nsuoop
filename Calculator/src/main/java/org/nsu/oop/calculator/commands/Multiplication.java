package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Multiplication implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        double a = context.popValue();
        double b = context.popValue();
        context.pushValue(a * b);
    }
}
