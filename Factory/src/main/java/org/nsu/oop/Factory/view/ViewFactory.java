package org.nsu.oop.Factory.view;

import org.nsu.oop.Factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFactory extends JPanel implements ActionListener {

    private final Factory factory;

    public ViewFactory(Factory factory) {
        this.factory = factory;
        setBackground(Color.LIGHT_GRAY);
        Timer timer = new Timer(500, this);
        timer.start();
        factory.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Size of Storage Car: " + factory.factoryInfo.getStorageAutoSize(), 10, 25);
        g.drawString("Size of Storage Motor: " + factory.factoryInfo.getStorageMotorSize(), 10, 50);
        g.drawString("Size of Storage Body: " + factory.factoryInfo.getStorageBodySize(), 10, 75);
        g.drawString("Size of Storage Accessory: " + factory.factoryInfo.getStorageAccessorySize(), 10, 100);
        g.drawString("Count of Accessory Supplier: " + factory.factoryInfo.getAccessorySuppliers(), 10, 125);
        g.drawString("Count of Workers: " + factory.factoryInfo.getWorkers(), 10, 150);
        g.drawString("Count of Dealers: " + factory.factoryInfo.getWorkers(), 10, 175);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
