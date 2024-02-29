package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;
import java.lang.Math;

public class Sqrt implements Command {

    @Override
    public void initial(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void runCommand(ExecutionContext context) {
        double value = context.popValue();
        context.pushValue(Math.sqrt(value));
    }
}
