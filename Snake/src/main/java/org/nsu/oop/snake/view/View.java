package org.nsu.oop.snake.view;

import org.nsu.oop.snake.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class View extends JPanel {

    Controller controller;

    private Image apple;
    private Image snake;
    private Image head;

    private final int sizeOfField;


    public View(KeyListener KeyTrigers, Controller controller) {
        setBackground(Color.GRAY);
        getImages();
        this.controller = controller;
        sizeOfField = controller.getSizeOfField();
        addKeyListener(KeyTrigers);
        setFocusable(true);
    }


    public void getImages() {
        ImageIcon imageIconApple = new ImageIcon("src/main/resources/apple.png");
        apple = imageIconApple.getImage();
        ImageIcon imageIconSnake = new ImageIcon("src/main/resources/snake.jpg");
        snake = imageIconSnake.getImage();
        ImageIcon imageIconHead = new ImageIcon("src/main/resources/head.jpg");
        head = imageIconHead.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controller.gameIsRun()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score: " + (controller.getSizeOfSnake() - 3), sizeOfField / 2 - 50, 25);
            for (int i = controller.getSizeOfSnake(); i > 0; --i) {
                g.drawImage(snake, controller.getX(i), controller.getY(i), this);
            }
            g.drawImage(apple, controller.getAppleX(), controller.getAppleY(), this);
            g.drawImage(head, controller.getX(0), controller.getY(0), this);
        } else if (controller.gameIsWin()) {
            String str = "Win";
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(str, sizeOfField / 2 - 20, sizeOfField / 2 - 30);
        } else {
            String str = "Game Over";
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(str, sizeOfField / 2 - 70, sizeOfField / 2 - 40);
        }
    }

}
