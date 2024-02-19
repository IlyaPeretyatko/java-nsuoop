package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;

public class Push implements Command {
    @Override
    public void command(Object[] args, ExecutionContext context) {
        if (args.length != 1) {
            //throw
        } else if (args[0] instanceof Double) {
            Double value = (Double)args[0];
            context.pushValue(value);
        } else if (args[0] instanceof String) {
            String arg = (String)args[0];
            double value = context.getValueOfVariable(arg);
            context.pushValue(value);
        } else {
            //throw
        }
    }
}
