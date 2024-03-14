package org.nsu.oop.calculator.exception.command;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String command) {
        super("Command \"" + command + "\" not found.");
    }
}
