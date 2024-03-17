package org.nsu.oop.snake.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Field extends JPanel implements ActionListener {
    private final int SIZE = 400;
    private final int CHUNK_SIZE = 20;
    private final int CHUNKS = 400;
    private Image apple;
    private Image snake;
    private Image head;
    private int appleX;
    private int appleY;
    private int[] x = new int[CHUNKS];
    private int[] y = new int[CHUNKS];
    private int sizeOfSnake;
    private Timer timer;
    private boolean up = false;
    private boolean right = true;
    private boolean down = false;
    private boolean left = false;

    private boolean gameIsRun = true;

    public Field() {
        setBackground(Color.CYAN);
        getImages();
        startGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void startGame() {
        sizeOfSnake = 3;
        for (int i = 0; i < sizeOfSnake; ++i) {
            x[i] = CHUNK_SIZE * (10 - i);
            y[i] = CHUNK_SIZE * 10;
        }
        spawnApple();
        timer = new Timer(200, this);
        timer.start();
    }

    public void spawnApple() {
        appleX = new Random().nextInt(20) * CHUNK_SIZE;
        appleY = new Random().nextInt(20) * CHUNK_SIZE;
    }

    public void getImages() {
        ImageIcon imageIconApple = new ImageIcon("src/main/resources/apple.png");
        apple = imageIconApple.getImage();
        ImageIcon imageIconSnake = new ImageIcon("src/main/resources/snake.jpg");
        snake = imageIconSnake.getImage();
        ImageIcon imageIconHead = new ImageIcon("src/main/resources/head.jpg");
        head = imageIconHead.getImage();
    }

    public void move() {
        for (int i = sizeOfSnake; i > 0; --i) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (up) {
            y[0] -= CHUNK_SIZE;
            if (y[0] < 0) {
             y[0] += SIZE;
            }
        } else if (down) {
            y[0] += CHUNK_SIZE;
            y[0] %= SIZE;
        } else if (right) {
            x[0] += CHUNK_SIZE;
            x[0] %= SIZE;
        } else if (left) {
            x[0] -= CHUNK_SIZE;
            if (x[0] < 0) {
                x[0] += SIZE;
            }
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            sizeOfSnake++;
            spawnApple();
        }
    }

    public void checkCollision() {
        for (int i = 1; i < sizeOfSnake; ++i) {
            if (sizeOfSnake > 4 && x[0] == x[i] && y[0] == y[i]) {
                gameIsRun = false;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 1; i < sizeOfSnake; ++i) {
            g.drawImage(snake, x[i], y[i], this);
        }
        g.drawImage(apple, appleX, appleY, this);
        g.drawImage(head, x[0], y[0], this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameIsRun) {
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            } else if (key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }
}
