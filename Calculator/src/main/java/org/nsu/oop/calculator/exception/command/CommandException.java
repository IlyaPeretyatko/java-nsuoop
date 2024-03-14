package org.nsu.oop.calculator.exception.command;

public class CommandException extends RuntimeException {
    public CommandException(String msg) {
        super("CommandException: " + msg);
    }
}
