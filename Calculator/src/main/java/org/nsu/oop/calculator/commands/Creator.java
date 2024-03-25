package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.Main;
import org.nsu.oop.calculator.exception.command.CommandNotFoundException;
import org.nsu.oop.calculator.exception.stream.InputStreamException;
import org.nsu.oop.calculator.exception.command.InvalidCreateCommandException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Logger;

public class Creator {

    private static final Logger log = Logger.getLogger(Creator.class.getName());

    private final Properties properties;

    public Creator() {
        ClassLoader cl = Main.class.getClassLoader();
        properties = new Properties();
        try (InputStream resourceAsStream = cl.getResourceAsStream("commands.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            log.warning("InputStreamException.");
            throw new InputStreamException();
        }
        log.info("Initialization Creator.");
    }

    public Command create(String commandName) throws CommandNotFoundException, InvalidCreateCommandException {
        String cmdName = properties.getProperty(commandName.toUpperCase());
        if (cmdName == null) {
            log.warning("CommandNotFoundException.");
            throw new CommandNotFoundException(commandName);
        }
        log.info("Get name of class command: " + cmdName + ".");
        try {
            return (Command) Class.forName(cmdName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            log.warning("InvalidCreateCommandException.");
            throw new InvalidCreateCommandException();
        }
    }


}
