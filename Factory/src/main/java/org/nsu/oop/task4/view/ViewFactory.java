package org.nsu.oop.task4.view;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.FactoryInfo;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewFactory extends JPanel implements ActionListener {

    private final Factory factory;
    private final FactoryInfo factoryInfo;
    private List<SliderLabel> sliders;

    public ViewFactory(Factory factory, FactoryInfo factoryInfo) {
        this.factory = factory;
        this.factoryInfo = factoryInfo;
        setBackground(Color.LIGHT_GRAY);
        setSliders();
        Timer timer = new Timer(100, this);
        timer.start();
        factory.start();
    }

    private void setSliders() {
        sliders = new ArrayList<>();
        String[] slidersText = {
                "        Body provider delay:",
                "       Motor provider delay:",
                "Accessory provider delay:",
                "   Dealers request delay:"
        };
        for (int i = 0; i < 4; i++) {
            SliderLabel sliderLabel = new SliderLabel(0, 1000, slidersText[i]);
            add(sliderLabel);
            sliders.add(sliderLabel);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Statistics", 250, 275);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Size of Storage Car: " + factoryInfo.getStorageAutoSize(), 10, 325);
        g.drawString("Size of Storage Motor: " + factoryInfo.getStorageMotorSize(), 10, 350);
        g.drawString("Size of Storage Body: " + factoryInfo.getStorageBodySize(), 10, 375);
        g.drawString("Size of Storage Accessory: " + factoryInfo.getStorageAccessorySize(), 10, 400);
        g.drawString("Count of Accessory Supplier: " + factoryInfo.getAccessorySuppliers(), 10, 425);
        g.drawString("Count of Workers: " + factoryInfo.getWorkers(), 10, 450);
        g.drawString("Count of Dealers: " + factoryInfo.getWorkers(), 10, 475);
        g.drawString("Produced cars: " + factoryInfo.getProduced(), 10, 500);
        g.drawString("Cars in order: " + factory.getInQueue(), 10, 525);
        g.drawString("Cars in storage: " + factory.getInStorageCar(), 10, 550);
        g.drawString("Bodies in storage: " + factory.getInStorageBody(), 10, 575);
        g.drawString("Motors in storage: " + factory.getInStorageMotor(), 10, 600);
        g.drawString("Accessories in storage: " + factory.getInStorageAccessory(), 10, 625);
        factory.setFreqBodySupplier(sliders.get(0).getSliderValue());
        factory.setFreqMotorSupplier(sliders.get(1).getSliderValue());
        factory.setFreqAccessorySupplier(sliders.get(2).getSliderValue());
        factory.setFreqDealer(sliders.get(3).getSliderValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }


}
