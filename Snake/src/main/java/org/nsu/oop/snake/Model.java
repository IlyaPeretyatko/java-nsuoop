package org.nsu.oop.snake;

import java.util.Random;

public class Model {
    public static final int SIZE = 400;
    public static final int CHUNK_SIZE = 20;
    public static final int CHUNKS = 400;
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

    public void setSizeOfSnake(int sizeOfSnake) {
        this.sizeOfSnake = sizeOfSnake;
    }

    public int getSizeOfSnake() {
        return this.sizeOfSnake;
    }

    public void setX(int index, int pos) {
        x[index] = pos;
    }

    public void setY(int index, int pos) {
        y[index] = pos;
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public boolean gameIsRun() {
        return isRun;
    }

    public void spawnApple() {
        appleX = new Random().nextInt(20) * CHUNK_SIZE;
        appleY = new Random().nextInt(20) * CHUNK_SIZE;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
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

}
