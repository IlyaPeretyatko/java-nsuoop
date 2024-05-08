package org.nsu.oop.Network.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ViewServer {
    private final Server server;
    private JFrame jFrame = new JFrame("Сервер");
    private JPanel jPanel = new JPanel();
    private JButton buttonStop = new JButton("Остановить сервер");

    public ViewServer(Server server) {
        this.server = server;
    }

    protected void displayFrame() {
        jPanel.add(buttonStop);
        jFrame.add(jPanel, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    server.stop();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.stop();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
