package org.nsu.oop.calculator.commands;


import org.nsu.oop.calculator.exception.command.InvalidCountOfArgsException;

import java.util.List;

public interface Command {

    void validateArgs(List<String> args) throws InvalidCountOfArgsException;

    List<String> getArgs();

}
