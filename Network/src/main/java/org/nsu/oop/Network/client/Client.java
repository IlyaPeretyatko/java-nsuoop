package org.nsu.oop.Network.client;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageManager;
import org.nsu.oop.Network.communicate.MessageType;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private String name;

    private Socket clientSocket;

    private static ViewClient viewClient;

    private MessageManager messageManager;

    Set<String> nameUsers;

    private boolean isConnect;

    public void startConnection() {
        if (!isConnect) {
            try {
                String ip = viewClient.getServerAddressFromOptionPane();
                int port = viewClient.getPortServerFromOptionPane();
                clientSocket = new Socket();
                clientSocket.connect(new InetSocketAddress(ip, port), 1000);
                isConnect = true;
                try (MessageManager messageManager = new MessageManager(clientSocket)) {
                    this.messageManager = messageManager;
                    loginUser();
                    communicatingWithServer();
                }
                clientSocket.close();
                System.exit(0);
            } catch (IOException | ClassNotFoundException e) {
                viewClient.errorDialogWindow("Not connected.");
            }
        }
    }

    public void stopConnection() {
        try {
            if (isConnect) {
                messageManager.send(new Message(MessageType.DISABLE_USER, name));
                isConnect = false;
            }
        } catch (IOException e) {
            viewClient.errorDialogWindow("Error of stopping connection.");
        }
    }

    private void loginUser() throws IOException, ClassNotFoundException {
        while (true) {
            Message message = this.messageManager.receive();
            if (message.getMessageType() == MessageType.REQUEST_NAME_USER) {
                String name = viewClient.getNameUser();
                messageManager.send(new Message(MessageType.USER_NAME, name));
                this.name = name;
            } else if (message.getMessageType() == MessageType.NAME_USED) {
                viewClient.errorDialogWindow("Имя занято.");
                String name = viewClient.getNameUser();
                messageManager.send(new Message(MessageType.USER_NAME, name));
                this.name = name;
            } else if (message.getMessageType() == MessageType.NAME_ACCEPTED) {
                viewClient.addMessage("Сервер: Вы подключились!\n");
                nameUsers = new HashSet<>(message.getNameUsers());
                viewClient.refreshListUsers(nameUsers);
                break;
            }
        }
    }

    private void communicatingWithServer() throws IOException, ClassNotFoundException {
        while (isConnect) {
            Message message = this.messageManager.receive();
            if (message.getMessageType() == MessageType.USER_ADDED) {
                String name = message.getText();
                if (!name.equals(this.name)) {
                    nameUsers.add(name);
                    viewClient.refreshListUsers(nameUsers);
                    viewClient.addMessage("Сервер: " + name + " подключился.\n");
                }
            } else if (message.getMessageType() == MessageType.TEXT_MESSAGE) {
                viewClient.addMessage(message.getText() + "\n");
            } else if (message.getMessageType() == MessageType.REMOVED_USER) {
                String name = message.getText();
                nameUsers.remove(name);
                viewClient.refreshListUsers(nameUsers);
                viewClient.addMessage("Сервер: " + name + " отключился.\n");
                if (name.equals(this.name)) {
                    break;
                }
            } else if (message.getMessageType() == MessageType.SERVER_STOP) {
                stopConnection();
                break;
            }
        }
    }

    public void sendTextToOtherClients(Message message) {
        try {
            messageManager.send(message);
        } catch (IOException e) {
            viewClient.errorDialogWindow("Ошибка отправки сообщения другим клиентам.");
        }
    }

    public boolean getIsConnect() {
        return isConnect;
    }

    public static void main(String[] args) {
        Client client = new Client();
        viewClient = new ViewClient(client);
        viewClient.displayFrame();
        client.startConnection();
    }
}
