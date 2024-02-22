package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.runtime.ExecutionContext;
import java.util.List;

public interface Command {
    public void runCommand(List<String> args, ExecutionContext context);
}
