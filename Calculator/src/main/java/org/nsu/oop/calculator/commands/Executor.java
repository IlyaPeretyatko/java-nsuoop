package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.Calculator;
import org.nsu.oop.calculator.ExecutionContext;
import org.nsu.oop.calculator.exception.command.MapNotContainVariableException;
import org.nsu.oop.calculator.exception.command.MethodNotFoundException;
import org.nsu.oop.calculator.exception.command.StackIsEmptyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Executor {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    Command currentCommand;
    ExecutionContext currentContext;

    public Executor(Command command, ExecutionContext context) {
        currentCommand = command;
        currentContext = context;
        log.info("Initialization Executor.");
    }

    public void searchMethod() throws MethodNotFoundException, StackIsEmptyException, MapNotContainVariableException {
        Class<?> clss = currentCommand.getClass();
        List<String> args = currentCommand.getArgs();
        boolean isFound = false;
        log.info("Start search method.");
        for (Method m: clss.getDeclaredMethods()) {
            if (!m.getName().equals("runCommand")) {
                continue;
            }
            List<Type> parametrs = Arrays.stream(m.getParameters()).map(it -> it.getType()).collect(Collectors.toList());
            parametrs.remove(parametrs.getFirst());
            int size = parametrs.size();
            if (parametrs.isEmpty()) {
                invokeWithoutParams(m);
                isFound = true;
                break;
            } else if (size == 1 && parametrs.getFirst().equals(java.lang.String.class) && !isNumeric(args.getFirst())) {
                invokeWithString(m);
                isFound = true;
                break;
            } else if (size == 1 && parametrs.getFirst().equals(java.lang.Double.class) && isNumeric(args.getFirst())) {
                invokeWithDouble(m);
                isFound = true;
                break;
            } else if (size == 2 && parametrs.getFirst().equals(java.lang.String.class) && parametrs.getLast().equals(java.lang.Double.class) && !isNumeric(args.getFirst()) && isNumeric(args.getLast())) {
                invokeWithStringDouble(m);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            log.warning("MethodNotFoundException.");
            throw new MethodNotFoundException();
        }
    }

    private void invokeWithoutParams(Method m) throws StackIsEmptyException, MapNotContainVariableException {
        log.info("Method is found.");
        try {
            m.invoke(currentCommand, currentContext);
        } catch (IllegalAccessException e) {
            log.warning("Method invoke problem.");
        } catch (InvocationTargetException e) {
            if (e.getCause().getClass().equals(StackIsEmptyException.class)) {
                throw (StackIsEmptyException) e.getCause();
            } else if (e.getCause().getClass().equals(MapNotContainVariableException.class)) {
                throw (MapNotContainVariableException) e.getCause();
            }
        }
    }

    private void invokeWithString(Method m) throws MapNotContainVariableException, StackIsEmptyException {
        log.info("Method is found.");
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst());
        } catch (IllegalAccessException e) {
            log.warning("Method invoke problem.");
        } catch (InvocationTargetException e) {
            if (e.getCause().getClass().equals(StackIsEmptyException.class)) {
                throw (StackIsEmptyException) e.getCause();
            } else if (e.getCause().getClass().equals(MapNotContainVariableException.class)) {
                throw (MapNotContainVariableException) e.getCause();
            }
        }
    }

    private void invokeWithDouble(Method m) throws StackIsEmptyException, MapNotContainVariableException {
        log.info("Method is found.");
        try {
            m.invoke(currentCommand, currentContext, Double.parseDouble(currentCommand.getArgs().getFirst()));
        } catch (IllegalAccessException e) {
            log.warning("Method invoke problem.");
        } catch (InvocationTargetException e) {
            if (e.getCause().getClass().equals(StackIsEmptyException.class)) {
                throw (StackIsEmptyException) e.getCause();
            } else if (e.getCause().getClass().equals(MapNotContainVariableException.class)) {
                throw (MapNotContainVariableException) e.getCause();
            }
        }
    }

    private void invokeWithStringDouble(Method m) throws StackIsEmptyException, MapNotContainVariableException {
        log.info("Method is found.");
        try {
            m.invoke(currentCommand, currentContext, currentCommand.getArgs().getFirst(), Double.parseDouble(currentCommand.getArgs().getLast()));
        } catch (IllegalAccessException e) {
            log.warning("Method invoke problem.");
        } catch (InvocationTargetException e) {
            if (e.getCause().getClass().equals(StackIsEmptyException.class)) {
                throw (StackIsEmptyException) e.getCause();
            } else if (e.getCause().getClass().equals(MapNotContainVariableException.class)) {
                throw (MapNotContainVariableException) e.getCause();
            }
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.?[0-9]*");
    }
}
