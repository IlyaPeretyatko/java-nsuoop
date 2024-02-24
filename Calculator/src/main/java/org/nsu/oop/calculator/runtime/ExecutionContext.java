package org.nsu.oop.calculator.runtime;

import java.util.Stack;
import java.util.HashMap;
import java.lang.Double;


public class ExecutionContext {
    final private Stack<Double> stack_ = new Stack<>();
    final private HashMap<String, Double> map_ = new HashMap<>();

    public void pushValue(double value) {
        stack_.push(value);
    }

    public void pushVariable(String str, double value) {
        map_.put(str, value);
    }

    public double popValue() {
        return stack_.pop();
    }

    public double peekValue() {
        return stack_.peek();
    }

    public double getValueOfVariable(String key) {
        return map_.get(key);
    }

}
