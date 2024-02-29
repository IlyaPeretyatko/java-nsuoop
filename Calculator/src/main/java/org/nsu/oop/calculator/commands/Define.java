package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.util.List;

public class Define implements Command {
    
    String variable;
    Double value;

    @Override
    public void initial(List<String> args) {
        if (args.size() != 2 || isNumeric(args.getFirst()) || !isNumeric(args.getLast())) {
            throw new IllegalArgumentException();
        }
        variable = args.getFirst();
        value = Double.parseDouble(args.getLast());
    }

    @Override
    public void runCommand(ExecutionContext context) {
        context.pushVariable(variable, value);
    }
}