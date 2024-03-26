package org.nsu.oop.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import static org.nsu.oop.snake.Model.CHUNK_SIZE;
import static org.nsu.oop.snake.Model.SIZE;

public class ViewControllerSwing extends JPanel implements ActionListener {

    private Image apple;
    private Image snake;
    private Image head;

    Model gameModel = new Model();

    public ViewControllerSwing() {
        setBackground(Color.GRAY);
        getImages();
        startGame();
        addKeyListener(new Controller());
        setFocusable(true);
    }

    public void startGame() {
        gameModel.setSizeOfSnake(3);
        for (int i = 0; i < gameModel.getSizeOfSnake(); ++i) {
            gameModel.setX(i, CHUNK_SIZE * (10 - i));
            gameModel.setY(i, CHUNK_SIZE * 10);
        }
        gameModel.spawnApple();
        Timer timer = new Timer(100, this);
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
        if (gameModel.gameIsRun()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score: " + (gameModel.getSizeOfSnake() - 3), SIZE / 2 - 50, 25);
            for (int i = gameModel.getSizeOfSnake(); i > 0; --i) {
                g.drawImage(snake, gameModel.getX(i), gameModel.getY(i), this);
            }
            g.drawImage(apple, gameModel.getAppleX(), gameModel.getAppleY(), this);
            g.drawImage(head, gameModel.getX(0), gameModel.getY(0), this);
        } else {
            String str = "Game Over";
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(str, SIZE / 2 - 70, SIZE / 2 - 40);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (gameModel.gameIsRun()) {
            gameModel.checkApple();
            gameModel.move();
            gameModel.checkCollision();
        }
    }

    class Controller extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT){
                gameModel.setDirection(3);
            } else if (key == KeyEvent.VK_RIGHT){
                gameModel.setDirection(1);
            } else if (key == KeyEvent.VK_UP){
                gameModel.setDirection(0);
            } else if (key == KeyEvent.VK_DOWN){
                gameModel.setDirection(2);
            }

        }

    }


}
