package org.nsu.oop.calculator;


import org.nsu.oop.calculator.commands.Command;
import org.nsu.oop.calculator.commands.Creator;
import org.nsu.oop.calculator.commands.Executor;
import org.nsu.oop.calculator.exception.command.*;


import java.util.List;
import java.util.logging.Logger;

public class Calculator {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private final ExecutionContext currentContext;
    private final Creator commandCreator;

    public Calculator() {
        currentContext = new ExecutionContext();
        commandCreator = new Creator();
        log.info("Initialization Calculator.");
    }

    public void run(String commandName, List<String> args) {
        try {
            Command command = commandCreator.create(commandName);
            log.info("Initialization command.");
            command.validateArgs(args);
            log.info("Validated args.");
            Executor executor = new Executor(command, currentContext);
            executor.searchMethod();
        } catch (MethodNotFoundException | CommandNotFoundException | InvalidCountOfArgsException |
                 InvalidCreateCommandException | StackIsEmptyException | MapNotContainVariableException e) {
            System.err.println(e.getMessage());
        }

    }
}
