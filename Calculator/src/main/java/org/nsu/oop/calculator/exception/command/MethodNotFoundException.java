package org.nsu.oop.calculator.exception.command;



public class MethodNotFoundException extends CommandException {
    public MethodNotFoundException() {
        super("Method with valid arguments not found.");
    }
}
