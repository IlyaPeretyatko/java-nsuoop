package org.nsu.oop.calculator;

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

    public double popValue() {
        log.info("Pop value from stack.");
        return stack_.pop();
    }

    public double peekValue() {
        log.info("Peek value from stack.");
        return stack_.peek();
    }

    public double getValueOfVariable(String key) {
        log.info("Get value of variable with key \"" + key + "\" from map." );
        return map_.get(key);
    }


}
