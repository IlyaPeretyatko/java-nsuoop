package org.nsu.oop.task4.view;

import javax.swing.*;
import java.awt.*;

public class SliderLabel extends JPanel {

    private int minValue;
    private int maxValue;

    JSlider slider;

    public SliderLabel(int minValue, int maxValue, String sliderDesc) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        setBackground(Color.LIGHT_GRAY);
        addText(sliderDesc);
        addSlider();
        setVisible(true);
    }

    public int getSliderValue() {
        return slider.getValue();
    }

    private void addText(String sliderDesc) {
        JLabel text = new JLabel();
        text.setText(sliderDesc);
        text.setVerticalAlignment(JLabel.TOP);
        text.setHorizontalAlignment(JLabel.LEFT);
        text.setOpaque(false);
        add(text);
    }

    private void addSlider() {
        slider = new JSlider();
        slider.setMinimum(minValue);
        slider.setMaximum(maxValue);
        slider.setOrientation(JSlider.HORIZONTAL);
        slider.setValue(maxValue);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(maxValue / 4);
        slider.setOpaque(false);
        add(slider);
    }

}