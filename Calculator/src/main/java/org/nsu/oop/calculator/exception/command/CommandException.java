package org.nsu.oop.calculator.exception.command;

public class CommandException extends Exception {
    public CommandException(String msg) {
        super("CommandException: " + msg);
    }
}
