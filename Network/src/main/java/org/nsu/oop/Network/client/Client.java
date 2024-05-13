package org.nsu.oop.Network.client;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageManager;
import org.nsu.oop.Network.communicate.MessageType;

import java.net.*;
import java.io.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private String name;

    Set<String> nameUsers;

    private static ViewClient viewClient;

    private MessageManager messageManager;

    private SocketChannel socketChannel;

    private Selector selector;

    private boolean isConnect;

    public void startConnection() {
        try {
            connectToServer();
            isConnect = true;
            messageManager = new MessageManager(socketChannel);
            selector = Selector.open();
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
            messageProcessing(selector, key);

        } catch (IOException | ClassNotFoundException e) {
            viewClient.errorDialogWindow("Not connected.");
        }
    }

    private void connectToServer() throws IOException {
        String ip = viewClient.getServerAddressFromOptionPane();
        Integer port = viewClient.getPortServerFromOptionPane();
        socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
        socketChannel.configureBlocking(false);
    }

    private void messageProcessing(Selector selector, SelectionKey key) throws IOException, ClassNotFoundException {
        while (isConnect) {
            selector.select();
            if (key.isReadable()) {
                interactionWithServer();
            }
        }
    }

    public void stopConnection() {
        try {
            if (isConnect) {
                isConnect = false;
                messageManager.send(new Message(MessageType.DISABLE_USER, name));
                socketChannel.close();
                selector.close();
                System.exit(0);
            }
        } catch (IOException e) {
            viewClient.errorDialogWindow("Error of stopping connection.");
        }
    }

    private void interactionWithServer() throws IOException, ClassNotFoundException {
        Message message = messageManager.receive();
        if (message.getMessageType() == MessageType.REQUEST_NAME_USER) {
            String name = viewClient.getNameUser();
            messageManager.send(new Message(MessageType.USER_NAME, name));
            this.name = name;
        } else if (message.getMessageType() == MessageType.NAME_USED) {
            viewClient.errorDialogWindow("Имя занято или не подходит.");
            String name = viewClient.getNameUser();
            messageManager.send(new Message(MessageType.USER_NAME, name));
            this.name = name;
        } else if (message.getMessageType() == MessageType.NAME_ACCEPTED) {
            viewClient.addMessage("Сервер: Вы подключились!\n");
            nameUsers = new HashSet<>(message.getNameUsers());
            viewClient.refreshListUsers(nameUsers);
        } else if (message.getMessageType() == MessageType.USER_ADDED) {
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
        } else if (message.getMessageType() == MessageType.SERVER_STOP) {
            socketChannel.close();
            System.exit(0);
        }
    }

    public void sendTextToOtherClients(String msg) {
        try {
            messageManager.send(new Message(MessageType.TEXT_MESSAGE, name + ": " + msg));
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
