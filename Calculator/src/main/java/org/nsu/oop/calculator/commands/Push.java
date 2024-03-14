package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;

import java.util.List;


public class Push implements Command {

    private List<String> args;


    @Override
    public void validateArgs(List<String> args) {
        if (args.size() != 1) {
            throw new InvalidCountOfArgsException("PUSH", 1);
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    public void runCommand(ExecutionContext currentContext, Double value) {
        currentContext.pushValue(value);
    }

    public void runCommand(ExecutionContext currentContext, String variable) {
        double value = currentContext.getValueOfVariable(variable);
        currentContext.pushValue(value);
    }
}
