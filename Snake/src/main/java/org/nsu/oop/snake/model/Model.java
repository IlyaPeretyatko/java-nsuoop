package org.nsu.oop.snake.model;

import java.util.Random;

public class Model {
    protected final int SIZE = 400;
    protected final int CHUNK_SIZE = 20;
    protected final int CHUNKS = 400;

    protected int appleX;
    protected int appleY;
    protected final int[] x = new int[CHUNKS];
    protected final int[] y = new int[CHUNKS];
    protected int sizeOfSnake;

    protected boolean up = false;
    protected boolean right = true;
    protected boolean down = false;
    protected boolean left = false;

    protected boolean isRun = true;
    protected boolean isWin = false;

    protected Model() {}

    protected void spawnApple() {
        appleX = new Random().nextInt(20) * CHUNK_SIZE;
        appleY = new Random().nextInt(20) * CHUNK_SIZE;
    }


    protected void move() {
        for (int i = sizeOfSnake; i > 0; i--) {
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

    protected void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            sizeOfSnake++;
            if (sizeOfSnake == 103) {
                isWin = true;
                isRun = false;
            }
            spawnApple();
        }
    }

    protected void checkCollision() {
        if (sizeOfSnake > 4) {
            for (int i = sizeOfSnake; i > 0 ; --i) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    isRun = false;
                    break;
                }
            }
        }
    }

}
