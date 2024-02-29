package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Pop implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        context.popValue();

    }
}
