package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

import java.util.List;

public class Print implements Command {
    @Override
    public void runCommand(List<String> args, ExecutionContext context) {
        System.out.println(context.peekValue());
    }
}


