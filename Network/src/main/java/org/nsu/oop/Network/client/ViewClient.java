package org.nsu.oop.Network.client;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

public class ViewClient {
    private final Client client;
    private final JFrame jFrame = new JFrame("Чат");
    private final JTextArea messages = new JTextArea(30, 20);
    private final JTextArea users = new JTextArea(30, 15);
    private final JPanel jPanel = new JPanel();
    private final JTextField textField = new JTextField(40);
    private final JButton buttonPrint = new JButton("Отправить");

    public ViewClient(Client client) {
        this.client = client;
    }

    protected void displayFrame() {
        messages.setEditable(false);
        users.setEditable(false);
        jFrame.add(new JScrollPane(messages), BorderLayout.CENTER);
        jFrame.add(new JScrollPane(users), BorderLayout.EAST);
        jPanel.add(textField);
        jPanel.add(buttonPrint);
        jFrame.add(jPanel, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.stopConnection();
            }
        });

        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendTextToOtherClients(textField.getText());
                textField.setText("");
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendTextToOtherClients(textField.getText());
                textField.setText("");
            }
        });
    }

    protected void addMessage(String text) {
        messages.append(text);
    }

    protected void refreshListUsers(Set<String> listUsers) {
        users.setText("");
        if (client.getIsConnect()) {
            StringBuilder text = new StringBuilder("Список пользователей:\n");
            for (String user : listUsers) {
                text.append(user).append("\n");
            }
            users.append(text.toString());
        }
    }

    protected String getServerAddressFromOptionPane() {
        return JOptionPane.showInputDialog(
                jFrame, "Введите адрес сервера:",
                "Ввод адреса сервера",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    protected Integer getPortServerFromOptionPane() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    jFrame, "Введите порт сервера:",
                    "Ввод порта сервера",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (port == null) {
                return null;
            }
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        jFrame, "Введен неккоректный порт сервера. Попробуйте еще раз.",
                        "Ошибка ввода порта сервера", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    protected String getNameUser() {
        return JOptionPane.showInputDialog(
                jFrame, "Введите имя пользователя:",
                "Ввод имени пользователя",
                JOptionPane.QUESTION_MESSAGE
        );
    }

    protected void errorDialogWindow(String text) {
        JOptionPane.showMessageDialog(
                jFrame, text,
                "Ошибка", JOptionPane.ERROR_MESSAGE
        );
    }
}