package org.nsu.oop.snake;

import javax.swing.*;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 435);
        setLocation(400, 400);
        add(new FieldModel());
        setVisible(true);
    }


}
