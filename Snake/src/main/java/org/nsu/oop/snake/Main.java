package org.nsu.oop.snake;

import javax.swing.*;

public class Main {

    public static JFrame jFrame;

    public static void main(String[] args) {
        jFrame = new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 435);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new ViewControllerSwing());
        jFrame.setVisible(true);
    }
}
