package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.lang.Class;

public class Push implements Command {

    ExecutionContext context;
    Class<?>[] params;
    Object arg;

    @Override
    public void runCommand(ExecutionContext context) {
        this.context = context;
        try {
            Push.class.getDeclaredMethod("push", params).invoke(this, arg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initial(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException();
        }
        params = new Class[1];
        String argument = args.getFirst();
        if (isNumeric(argument)) {
            params[0] = Double.class;
            arg = Double.parseDouble(argument);
        } else {
            params[0] = String.class;
            arg = argument;
        }
    }

    private void push(Double value) {
        context.pushValue(value);
    }

    private void push(String variable) {
        double value = context.getValueOfVariable(variable);
        context.pushValue(value);
    }
}
