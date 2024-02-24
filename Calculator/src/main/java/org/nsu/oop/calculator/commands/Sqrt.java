package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;
import java.lang.Math;

public class Sqrt implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (args.isEmpty()) {
            double value = context.popValue();
            context.pushValue(Math.sqrt(value));
        } else {
            //throw
        }
    }
}
