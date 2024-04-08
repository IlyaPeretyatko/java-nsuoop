package org.nsu.oop.snake.controller;


import org.nsu.oop.snake.model.Model;
import org.nsu.oop.snake.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener {

    Model model;
    View view;

    private int speed;
    private final Timer timer;

    public Controller() {
        model = new Model();
        view = new View(new KeyTrigers(), this);
        speed = 250;
        timer = new Timer(speed, this);
        timer.start();
        startGame();
    }

    public void startGame() {
        createSnake();
        model.spawnApple();
    }

    public void createSnake() {
        model.setSizeOfSnake(3);
        for (int i = 0; i < model.getSizeOfSnake(); ++i) {
            int[] x = new int[model.getSIZE()];
            int[] y = new int[model.getSIZE()];
            x[i] =  model.getCHUNKSIZE() * (10 - i);
            y[i] = model.getCHUNKSIZE() * 10;
            model.setX(x);
            model.setY(y);
        }
    }

    public View getView() { return view; }

    public int getAppleX() { return model.getAppleX(); }

    public int getAppleY() {
        return model.getAppleY();
    }

    public int getSizeOfSnake() { return model.getSizeOfSnake(); }

    public int getX(int index) {
        int[] x = model.getX();
        return x[index];
    }

    public int getY(int index) {
        int[] y = model.getY();
        return y[index];
    }

    public boolean gameIsRun() {
        return model.isRun();
    }

    public boolean gameIsWin() { return model.isWin(); }

    public int getSizeOfField() { return model.getSIZE(); }


    @Override
    public void actionPerformed(ActionEvent e) {
        view.repaint();
        if (gameIsRun()) {
            model.checkApple();
            model.move();
            model.checkCollision();

        }
    }


    class KeyTrigers extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT){
                model.setDirection(3);
            } else if (key == KeyEvent.VK_RIGHT){
                model.setDirection(1);
            } else if (key == KeyEvent.VK_UP){
                model.setDirection(0);
            } else if (key == KeyEvent.VK_DOWN){
                model.setDirection(2);
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
