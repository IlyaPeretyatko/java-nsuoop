package org.nsu.oop.calculator.exception.command;

import org.nsu.oop.calculator.exception.command.CommandException;

public class InvalidCreateCommandException extends CommandException {
    public InvalidCreateCommandException() {
        super("Error creating command instance.");
    }
}
