package org.nsu.oop.snake.controller;


import org.nsu.oop.snake.model.Model;

public class Controller extends Model {


    public int getAppleX() { return appleX; }

    public int getAppleY() {
        return appleY;
    }

    public int getSizeOfSnake() {
        return this.sizeOfSnake;
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public void setDirection(int direction) {
        if (direction == 0) {
            if (!down) {
                right = false;
                up = true;
                left = false;
            }
        } else if (direction == 1) {
            if (!left) {
                right = true;
                up = false;
                down = false;
            }
        } else if (direction == 2) {
            if (!up) {
                right = false;
                down = true;
                left = false;
            }
        } else if (direction == 3) {
            if (!right) {
                left = true;
                up = false;
                down = false;
            }
        }
    }

    public boolean gameIsRun() {
        return isRun;
    }

    public boolean gameIsWin() { return isWin; }

    public void createSnake() {
        sizeOfSnake = 3;
        for (int i = 0; i < sizeOfSnake; ++i) {
            x[i] =  CHUNK_SIZE * (10 - i);
            y[i] = CHUNK_SIZE * 10;
        }
    }

    public void callMove() {
        move();
    }

    public void callCheckApple() {
        checkApple();
    }

    public void callCheckCollision() {
        checkCollision();
    }

    public void callSpawnApple() {
        spawnApple();
    }

    public int getSizeOfField() {
        return SIZE;
    }


}
