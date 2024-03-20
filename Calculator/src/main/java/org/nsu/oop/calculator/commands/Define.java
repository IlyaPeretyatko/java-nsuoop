package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;

import java.util.List;

public class Define implements Command {

    private List<String> args;


    @Override
    public void validateArgs(List<String> args) throws InvalidCountOfArgsException {
        if (args.size() != 2) {
            throw new InvalidCountOfArgsException("DEFINE", 2);
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }


    public void runCommand(ExecutionContext currentContext, String variable, Double value) {
        currentContext.pushVariable(variable, value);
    }
}