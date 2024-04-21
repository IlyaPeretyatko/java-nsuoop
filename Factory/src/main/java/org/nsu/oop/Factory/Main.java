package org.nsu.oop.Factory;

import org.nsu.oop.Factory.view.ViewFactory;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static JFrame jFrame;
    public static void main(String[] args) {
        try {
            Factory factory = new Factory();
            jFrame = new JFrame("Factory");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(600, 735);
            jFrame.setLocationRelativeTo(null);
            jFrame.add(new ViewFactory(factory));
            jFrame.setVisible(true);
        } catch (IOException e) {
            System.out.println("Не удалось считать характеристики фабркии.");
        }
    }
}
