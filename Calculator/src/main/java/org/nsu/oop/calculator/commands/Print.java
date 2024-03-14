package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;

import java.util.List;

public class Print implements Command {

    private List<String> args;

    public void runCommand(ExecutionContext currentContext) {
        System.out.println(currentContext.peekValue());
    }

    @Override
    public void validateArgs(List<String> args) {
        if (!args.isEmpty()) {
            throw new InvalidCountOfArgsException("PRINT", 0);
        }
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

}


