package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Define implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (args.size() != 2 || isNumeric(args.getFirst()) || !isNumeric(args.getLast())) {
            throw new IllegalArgumentException();
        }
        context.pushVariable(args.getFirst(), Double.parseDouble(args.getLast()));
    }
}