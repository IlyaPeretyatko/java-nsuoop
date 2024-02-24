package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Push implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (args.size() != 1) {
            // throw
        } else if (isNumeric(args.getFirst())) {
            double value = Double.parseDouble(args.getFirst());
            context.pushValue(value);
        } else {
            double value = context.getValueOfVariable(args.getFirst());
            context.pushValue(value);
        }
    }
}
