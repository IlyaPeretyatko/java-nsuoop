package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Print implements Command {
    @Override
    public void runCommand(ExecutionContext context) {
        System.out.println(context.peekValue());
    }

    @Override
    public void initial(List<String> args) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

}


