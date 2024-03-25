package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;
import org.nsu.oop.calculator.exception.command.MapNotContainVariableException;

import java.util.List;


public class Push implements Command {

    private List<String> args;


    @Override
    public void validateArgs(List<String> args) throws InvalidCountOfArgsException {
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

    public void runCommand(ExecutionContext currentContext, String variable) throws MapNotContainVariableException {
        double value = currentContext.getValueOfVariable(variable);
        currentContext.pushValue(value);
    }
}
