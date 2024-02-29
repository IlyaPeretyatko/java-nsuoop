package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;
import java.lang.Math;

public class Sqrt implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        double value = context.popValue();
        context.pushValue(Math.sqrt(value));
    }
}
