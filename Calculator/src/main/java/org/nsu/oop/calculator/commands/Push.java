package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import java.util.List;


public class Push implements Command {

    ExecutionContext currentContext;


    @Override
    public void initial(List<String> args, ExecutionContext context) {
        if (args.size() != 1) {
            throw new IllegalArgumentException();
        }
        currentContext = context;
    }

    public void runCommand(Double value) {
        currentContext.pushValue(value);
    }

    public void runCommand(String variable) {
        double value = currentContext.getValueOfVariable(variable);
        currentContext.pushValue(value);
    }
}
