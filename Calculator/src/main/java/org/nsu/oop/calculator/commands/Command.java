package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.ExecutionContext;
import java.util.List;

public interface Command {

    void initial(List<String> args, ExecutionContext context);

}
