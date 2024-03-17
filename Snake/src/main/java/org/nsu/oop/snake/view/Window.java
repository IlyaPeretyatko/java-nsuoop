package org.nsu.oop.snake.view;

import org.nsu.oop.snake.model.Field;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 435);
        setLocation(400, 400);
        add(new Field());
        setVisible(true);
    }


}
