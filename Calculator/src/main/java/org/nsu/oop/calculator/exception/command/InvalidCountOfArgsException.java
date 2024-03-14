package org.nsu.oop.calculator.exception.command;

import org.nsu.oop.calculator.exception.command.CommandException;

public class InvalidCountOfArgsException extends CommandException {
    public InvalidCountOfArgsException(String command, int count) {
        super("Invalid count of arguments for command " + command + ". Need: " + count + ".");
    }
}
