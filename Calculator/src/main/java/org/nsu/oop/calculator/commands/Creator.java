package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.Main;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Creator {
    private final Properties properties;

    public Creator() {
        ClassLoader cl = Main.class.getClassLoader();
        properties = new Properties();
        try (InputStream resourceAsStream = cl.getResourceAsStream("commands.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Command create(String commandName) {
        String cmdName = properties.getProperty(commandName.toUpperCase());
        try {
            return (Command) Class.forName(cmdName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
