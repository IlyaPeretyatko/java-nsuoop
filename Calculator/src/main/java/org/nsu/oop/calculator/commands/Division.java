package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

public class Division implements Command {
    @Override
    public void command(Object[] args, ExecutionContext context) {
        double a = context.popValue();
        double b = context.popValue();
        context.pushValue(a / b);
    }
}
