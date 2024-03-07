package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Print implements Command {

    ExecutionContext currentContext;

    public void runCommand() {
        System.out.println(currentContext.peekValue());
    }

    @Override
    public void initial(List<String> args, ExecutionContext context) {
        if (!args.isEmpty()) {
            throw new IllegalArgumentException();
        }
        currentContext = context;
    }

}


