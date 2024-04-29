package org.nsu.oop.task4.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryInfo {

    private final Properties properties;
    private int produced;

    public FactoryInfo() throws IOException {
        this.properties = new Properties();
        readPropertiesFabric();
    }

    public void realese() {
        produced += 1;
    }

    private void readPropertiesFabric() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream("factory.properties")) {
            properties.load(resourceAsStream);
        }
    }

    public int getProduced() { return produced; }

    public int getStorageBodySize() {
        return Integer.parseInt(properties.getProperty("StorageBodySize"));
    }

    public int getStorageMotorSize() {
        return Integer.parseInt(properties.getProperty("StorageMotorSize"));
    }

    public int getStorageAccessorySize() {
        return Integer.parseInt(properties.getProperty("StorageAccessorySize"));
    }

    public int getStorageAutoSize() {
        return Integer.parseInt(properties.getProperty("StorageAutoSize"));
    }

    public int getAccessorySuppliers() {
        return Integer.parseInt(properties.getProperty("AccessorySuppliers"));
    }

    public int getWorkers() {
        return Integer.parseInt(properties.getProperty("Workers"));
    }

    public int getDealers() {
        return Integer.parseInt(properties.getProperty("Dealers"));
    }

    public boolean getLogSale() {
        return Boolean.parseBoolean(properties.getProperty("LogSale"));
    }

}
