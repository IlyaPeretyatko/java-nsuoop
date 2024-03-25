package org.nsu.oop.calculator.exception.command;


public class InvalidCreateCommandException extends CommandException {
    public InvalidCreateCommandException() {
        super("Error creating command instance.");
    }
}
