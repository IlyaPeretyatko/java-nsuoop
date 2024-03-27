package org.nsu.oop.snake.view;

import org.nsu.oop.snake.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class View extends JPanel implements ActionListener {

    private Image apple;
    private Image snake;
    private Image head;

    private int sizeOfField;
    private int speed;
    private Timer timer;

    Controller controller = new Controller();

    public View() {
        setBackground(Color.GRAY);
        getImages();
        startGame();
        addKeyListener(new KeyTrigers());
        setFocusable(true);
    }

    public void startGame() {
        controller.createSnake();
        controller.callSpawnApple();
        sizeOfField = controller.getSizeOfField();
        speed = 250;
        timer = new Timer(speed, this);
        timer.start();
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


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (controller.gameIsRun()) {
            controller.callCheckApple();
            controller.callMove();
            controller.callCheckCollision();

        }
    }

    class KeyTrigers extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT){
                controller.setDirection(3);
            } else if (key == KeyEvent.VK_RIGHT){
                controller.setDirection(1);
            } else if (key == KeyEvent.VK_UP){
                controller.setDirection(0);
            } else if (key == KeyEvent.VK_DOWN){
                controller.setDirection(2);
            } else if (key == KeyEvent.VK_0) {
                if (speed != 100) {
                    speed -= 50;
                }
                timer.setDelay(speed);
            } else if (key == KeyEvent.VK_9) {
                if (speed != 500) {
                    speed += 50;
                }
                timer.setDelay(speed);
            }
        }

    }


}
