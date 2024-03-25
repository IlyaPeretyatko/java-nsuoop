package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;
import org.nsu.oop.calculator.exception.command.StackIsEmptyException;

import java.util.List;
import java.lang.Math;

public class Sqrt implements Command {

    private List<String> args;

    @Override
    public void validateArgs(List<String> args) throws InvalidCountOfArgsException {
        if (!args.isEmpty()) {
            throw new InvalidCountOfArgsException("SQRT", 0);
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    public void runCommand(ExecutionContext currentContext) throws StackIsEmptyException {
        double value = currentContext.popValue();
        currentContext.pushValue(Math.sqrt(value));
    }
}
