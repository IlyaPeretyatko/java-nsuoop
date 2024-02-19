package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

public interface Command {
    public void command(Object[] args, ExecutionContext context);
}
