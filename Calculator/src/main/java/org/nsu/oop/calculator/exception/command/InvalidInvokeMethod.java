package org.nsu.oop.calculator.exception.command;

import org.nsu.oop.calculator.exception.command.CommandException;

public class InvalidInvokeMethod extends CommandException {
    public InvalidInvokeMethod() {
        super("Error in invoke method. There may not be enough elements in the stack or map.");
    }
}
