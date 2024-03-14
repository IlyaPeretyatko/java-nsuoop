package org.nsu.oop.calculator.exception.command;

import org.nsu.oop.calculator.exception.command.CommandException;

public class MethodNotFoundException extends CommandException {
    public MethodNotFoundException() {
        super("Method with valid arguments not found.");
    }
}
