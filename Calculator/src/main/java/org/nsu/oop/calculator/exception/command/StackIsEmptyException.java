package org.nsu.oop.calculator.exception.command;

public class StackIsEmptyException extends CommandException {
    public StackIsEmptyException() {
        super("Enough of elements on stack. Push required count of elements and retry.");
    }
}
