package org.nsu.oop.calculator.commands;

import org.nsu.oop.calculator.Main;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.lang.Class;
import java.util.Properties;

public class Creator {
    private final Properties properties;

    public Creator() throws IOException {
        ClassLoader cl = Main.class.getClassLoader();
        properties = new Properties();
        InputStream resourceAsStream = cl.getResourceAsStream("commands.properties");
        properties.load(resourceAsStream);
    }

    public Command create(String commandName) {
        String cmdName = properties.getProperty(commandName.toUpperCase());
        try {
            return (Command) Class.forName(cmdName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            //throw
        }
        return null;
    }


}
