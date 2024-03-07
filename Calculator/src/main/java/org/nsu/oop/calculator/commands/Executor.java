package org.nsu.oop.calculator.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Executor {
    Command currentCommand;
    List<String> args;

    public Executor(Command command, List<String> args) {
        currentCommand = command;
        this.args = args;
    }

    public void searchMethod() {
        Class<?> clss = currentCommand.getClass();
        int size = args.size();

        for(Method m: clss.getDeclaredMethods()) {
            String parametrs = Arrays.stream(m.getParameters()).map(it -> it.getType().getName()).collect(Collectors.joining(", "));
            if (!m.getName().equals("runCommand")) {
                continue;
            }
            if (size == 0 && parametrs.isEmpty()) {
                invokeWithoutParams(m);
            } else if (size == 1 && parametrs.equals("java.lang.String") && !isNumeric(args.getFirst())) {
                invokeWithString(m);
            } else if (size == 1 && parametrs.equals("java.lang.Double") && isNumeric(args.getFirst())) {
                invokeWithDouble(m);
            } else if (size == 2 && parametrs.equals("java.lang.String, java.lang.Double")) {
                invokeWithStringDouble(m);
            }
        }
    }

    private void invokeWithoutParams(Method m) {
        try {
            m.invoke(currentCommand);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithString(Method m) {
        try {
            m.invoke(currentCommand, args.getFirst());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithDouble(Method m) {
        try {
            m.invoke(currentCommand, Double.parseDouble(args.getFirst()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeWithStringDouble(Method m) {
        if (isNumeric(args.getFirst()) && !isNumeric(args.getLast())) {
            throw new IllegalArgumentException();
        }
        try {
            m.invoke(currentCommand, args.getFirst(), Double.parseDouble(args.getLast()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNumeric(String str) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < str.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(str.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }

}
