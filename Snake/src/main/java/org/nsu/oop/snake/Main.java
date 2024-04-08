package org.nsu.oop.snake;

import org.nsu.oop.snake.controller.Controller;
import javax.swing.*;

public class Main {

    public static JFrame jFrame;

    public static void main(String[] args) {
        jFrame = new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 435);
        jFrame.setLocationRelativeTo(null);
        Controller controller = new Controller();
        jFrame.add(controller.getView());
        jFrame.setVisible(true);
    }
}
