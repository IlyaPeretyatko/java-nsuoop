package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Print implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        if (args.isEmpty()) {
            System.out.println(context.peekValue());
        } else {
            //throw
        }
    }
}


