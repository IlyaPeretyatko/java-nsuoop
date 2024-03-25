package org.nsu.oop.calculator;

import org.nsu.oop.calculator.exception.command.MapNotContainVariableException;
import org.nsu.oop.calculator.exception.command.StackIsEmptyException;

import java.util.Stack;
import java.util.HashMap;
import java.lang.Double;
import java.util.logging.Logger;


public class ExecutionContext {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    final private Stack<Double> stack_;
    final private HashMap<String, Double> map_;

    public ExecutionContext() {
        stack_ = new Stack<>();
        map_ = new HashMap<>();
        log.info("Initialization ExecutionContext.");
    }

    public void pushValue(double value) {
        log.info("Push value in stack: " + value + ".");
        stack_.push(value);
    }

    public void pushVariable(String str, double value) {
        log.info("Push value with key in map: " + str + "/" + value + ".");
        map_.put(str, value);
    }

    public double popValue() throws StackIsEmptyException {
        log.info("Pop value from stack.");
        if (stack_.isEmpty()) {
            log.warning("StackIsEmptyException");
            throw new StackIsEmptyException();
        }
        return stack_.pop();
    }

    public double peekValue() throws StackIsEmptyException {
        log.info("Peek value from stack.");
        if (stack_.isEmpty()) {
            log.warning("StackIsEmptyException");
            throw new StackIsEmptyException();
        }
        return stack_.peek();
    }

    public double getValueOfVariable(String key) throws MapNotContainVariableException {
        log.info("Get value of variable with key \"" + key + "\" from map." );
        Double value = map_.get(key);
        if (value == null) {
            log.warning("MapNotContainVariableException.");
            throw new MapNotContainVariableException();
        }
        return map_.get(key);
    }


}
