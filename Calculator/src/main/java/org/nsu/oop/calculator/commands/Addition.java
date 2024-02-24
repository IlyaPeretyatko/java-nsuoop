package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Addition implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (args.isEmpty()) {
            double a = context.popValue();
            double b = context.popValue();
            context.pushValue(a + b);
        } else {
            // throw
        }
    }
}
