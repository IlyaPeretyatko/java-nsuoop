package org.nsu.oop.task4;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.FactoryInfo;
import org.nsu.oop.task4.view.ViewFactory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    public static JFrame jFrame;

    public static void main(String[] args) {
        try {
            FactoryInfo factoryInfo = new FactoryInfo();
            Factory factory = new Factory(factoryInfo);
            jFrame = new JFrame("Factory");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            jFrame.setSize(600, 735);
            jFrame.setLocationRelativeTo(null);
            jFrame.add(new ViewFactory(factory, factoryInfo));
            jFrame.setVisible(true);
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int confirm = JOptionPane.showOptionDialog(jFrame,
                            "Close?", "Exit", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == JOptionPane.YES_OPTION) {
                        factory.stop();
                        System.exit(1);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Не удалось считать характеристики фабркии.");
        }
    }

}
