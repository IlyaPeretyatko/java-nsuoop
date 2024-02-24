package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;
import java.util.List;

public interface Command {
    void runCommand(List<String> args, ExecutionContext context);

    default boolean isNumeric(String str) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < str.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(str.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }
}
