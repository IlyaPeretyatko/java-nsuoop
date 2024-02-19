package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;
public class Define implements Command {
    @Override
    public void command(Object[] args, ExecutionContext context) {
        if (args.length != 2) {
            //throw
        } else if (args[0] instanceof String && args[1] instanceof Double) {
            String str = (String)args[0];
            Double value = (Double)args[1];
            context.pushVariable(str, value);
        } else {
            //throw
        }
    }
}