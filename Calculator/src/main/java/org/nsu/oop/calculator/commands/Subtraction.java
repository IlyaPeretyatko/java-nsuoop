package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;
import org.nsu.oop.calculator.exception.command.StackIsEmptyException;

import java.util.List;

public class Subtraction implements Command {

    private List<String> args;

    @Override
    public void validateArgs(List<String> args) throws InvalidCountOfArgsException {
        if (!args.isEmpty()) {
            throw new InvalidCountOfArgsException("-", 0);
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    public void runCommand(ExecutionContext currentContext) throws StackIsEmptyException {
        double a = currentContext.popValue();
        double b = currentContext.popValue();
        currentContext.pushValue(b - a);
    }
}
