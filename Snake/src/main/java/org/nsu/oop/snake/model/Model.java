package org.nsu.oop.snake.model;

import java.util.Random;

public class Model {
    private final int SIZE = 400;
    private final int CHUNK_SIZE = 20;
    private final int CHUNKS = 400;

    private int appleX;
    private int appleY;
    private final int[] x = new int[CHUNKS];
    private final int[] y = new int[CHUNKS];
    private int sizeOfSnake;

    private boolean up = false;
    private boolean right = true;
    private boolean down = false;
    private boolean left = false;

    private boolean isRun = true;
    private boolean isWin = false;


    public void createSnake() {
        sizeOfSnake = 3;
        for (int i = 0; i < sizeOfSnake; ++i) {
            x[i] =  CHUNK_SIZE * (10 - i);
            y[i] = CHUNK_SIZE * 10;
        }
    }

    public void spawnApple() {
        appleX = new Random().nextInt(20) * CHUNK_SIZE;
        appleY = new Random().nextInt(20) * CHUNK_SIZE;
    }


    public void move() {
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

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            sizeOfSnake++;
            if (sizeOfSnake == 103) {
                isWin = true;
                isRun = false;
            }
            spawnApple();
        }
    }

    public void checkCollision() {
        if (sizeOfSnake > 4) {
            for (int i = sizeOfSnake; i > 0 ; --i) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    isRun = false;
                    break;
                }
            }
        }
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

    public int getAppleX() {
        return appleX;
    }

    public boolean isRun() {
        return isRun;
    }

    public boolean isWin() {
        return isWin;
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getSizeOfSnake() {
        return sizeOfSnake;
    }

    public int getAppleY() {
        return appleY;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

}
