package org.nsu.oop.Factory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Factory factory = new Factory();
        } catch (IOException e) {
            System.out.println("Не удалось считать характеристики фабркии.");
        }

    }
}
