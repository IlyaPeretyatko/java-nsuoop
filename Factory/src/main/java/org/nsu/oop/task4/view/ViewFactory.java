package org.nsu.oop.task4.view;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.FactoryInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFactory extends JPanel implements ActionListener {

    private final Factory factory;
    private final FactoryInfo factoryInfo;

    public ViewFactory(Factory factory, FactoryInfo factoryInfo) {
        this.factory = factory;
        this.factoryInfo = factoryInfo;
        setBackground(Color.LIGHT_GRAY);
        Timer timer = new Timer(100, this);
        timer.start();
        factory.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Size of Storage Car: " + factoryInfo.getStorageAutoSize(), 10, 25);
        g.drawString("Size of Storage Motor: " + factoryInfo.getStorageMotorSize(), 10, 50);
        g.drawString("Size of Storage Body: " + factoryInfo.getStorageBodySize(), 10, 75);
        g.drawString("Size of Storage Accessory: " + factoryInfo.getStorageAccessorySize(), 10, 100);
        g.drawString("Count of Accessory Supplier: " + factoryInfo.getAccessorySuppliers(), 10, 125);
        g.drawString("Count of Workers: " + factoryInfo.getWorkers(), 10, 150);
        g.drawString("Count of Dealers: " + factoryInfo.getWorkers(), 10, 175);
        g.drawString("Produced cars: " + factoryInfo.getProduced(), 10, 200);
        g.drawString("Cars in order: " + factory.getInQueue(), 10, 225);
        g.drawString("Cars in storage: " + factory.getInStorageCar(), 10, 250);
        g.drawString("Bodies in storage: " + factory.getInStorageBody(), 10, 275);
        g.drawString("Motors in storage: " + factory.getInStorageMotor(), 10, 300);
        g.drawString("Accessories in storage: " + factory.getInStorageAccessory(), 10, 325);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


}
