package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

public class Print implements Command {
    @Override
    public void command(Object[] args, ExecutionContext context) {
        System.out.println(context.peekValue());
    }
}


