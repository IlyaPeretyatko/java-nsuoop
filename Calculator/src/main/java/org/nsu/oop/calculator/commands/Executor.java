package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Executor {
    Command currentCommand;
    ExecutionContext currentContext;

    public Executor(Command command, ExecutionContext context) {
        currentCommand = command;
        currentContext = context;
    }

    public void searchMethod() {
        Class<?> clss = currentCommand.getClass();
        List<String> args = currentCommand.getArgs();
        for (Method m: clss.getDeclaredMethods()) {
            if (!m.getName().equals("runCommand")) {
                continue;
            }
            String params = Arrays.stream(m.getParameters()).map(it -> it.getType().getName()).collect(Collectors.joining(", "));
            String parametrs;
            if (args.isEmpty()) {
                parametrs = params.substring("org.nsu.oop.calculator.commands.Division".toCharArray().length - 1);
            } else {
                parametrs = params.substring("org.nsu.oop.calculator.commands.Division, ".toCharArray().length - 1);
            }

            if (parametrs.isEmpty()) {
                invokeWithoutParams(m);
            } else if (parametrs.equals("java.lang.String") && !isNumeric(args.getFirst())) {
                invokeWithString(m);
            } else if (parametrs.equals("java.lang.Double") && isNumeric(args.getFirst())) {
                invokeWithDouble(m);
            } else if (parametrs.equals("java.lang.String, java.lang.Double") && !isNumeric(args.getFirst()) && isNumeric(args.getLast())) {
                invokeWithStringDouble(m);
            }
        }
    }

    private void invokeWithoutParams(Method m) {
        try {
            m.invoke(currentCommand, currentContext);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithString(Method m) {
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithDouble(Method m) {
        try {
            m.invoke(currentCommand, currentContext, Double.parseDouble(currentCommand.getArgs().getFirst()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithStringDouble(Method m) {
        if (isNumeric(currentCommand.getArgs().getFirst()) && !isNumeric(currentCommand.getArgs().getLast())) {
            throw new IllegalArgumentException();
        }
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst(), Double.parseDouble(currentCommand.getArgs().getLast()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.?[0-9]*");
    }

}
