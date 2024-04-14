package org.nsu.oop.Factory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FactoryInfo factoryInfo = new FactoryInfo();

        } catch (IOException e) {
            System.out.println("Не удалось считать характеристики фабркии.");
        }

    }
}
