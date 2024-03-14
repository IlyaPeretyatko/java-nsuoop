package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.InvalidInvokeMethod;
import org.nsu.oop.calculator.exception.command.MethodNotFoundException;

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
        boolean isFound = false;
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
                isFound = true;
                break;
            } else if (parametrs.equals("java.lang.String") && !isNumeric(args.getFirst())) {
                invokeWithString(m);
                isFound = true;
                break;
            } else if (parametrs.equals("java.lang.Double") && isNumeric(args.getFirst())) {
                invokeWithDouble(m);
                isFound = true;
                break;
            } else if (parametrs.equals("java.lang.String, java.lang.Double") && !isNumeric(args.getFirst()) && isNumeric(args.getLast())) {
                invokeWithStringDouble(m);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new MethodNotFoundException();
        }
    }

    private void invokeWithoutParams(Method m) {
        try {
            m.invoke(currentCommand, currentContext);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvalidInvokeMethod();
        }
    }

    private void invokeWithString(Method m) {
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvalidInvokeMethod();
        }
    }

    private void invokeWithDouble(Method m) {
        try {
            m.invoke(currentCommand, currentContext, Double.parseDouble(currentCommand.getArgs().getFirst()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvalidInvokeMethod();
        }
    }

    private void invokeWithStringDouble(Method m) {
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst(), Double.parseDouble(currentCommand.getArgs().getLast()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InvalidInvokeMethod();
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.?[0-9]*");
    }
}
