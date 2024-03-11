package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import java.util.List;

public interface Command {

    void validateArgs(List<String> args);

    List<String> getArgs();

}
