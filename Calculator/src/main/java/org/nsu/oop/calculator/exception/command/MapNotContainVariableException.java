package org.nsu.oop.calculator.exception.command;

public class MapNotContainVariableException extends CommandException {
    public MapNotContainVariableException() {
        super("There is no such variable in map.");
    }
}
