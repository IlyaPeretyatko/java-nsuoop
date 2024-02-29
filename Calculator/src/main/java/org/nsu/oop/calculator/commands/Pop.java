package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Pop implements Command {

    @Override
    public void initial(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void runCommand(ExecutionContext context) {
        context.popValue();
    }

}
