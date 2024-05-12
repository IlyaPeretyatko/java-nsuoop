package org.nsu.oop.Network.server;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageManager;
import org.nsu.oop.Network.communicate.MessageType;

import java.net.*;
import java.io.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;


public class Server {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private final Map<String, SelectionKey> users = new Hashtable<>();

    private static ViewServer viewServer;

    private boolean isRun;

    public void start(int port) {
        try {
            upServer(port);
            isRun = true;
            clientsProcessing();
        } catch (IOException | ClassNotFoundException e) {
            viewServer.errorDialogWindow("Error of working server..");
        }
    }

    private void upServer(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void clientsProcessing() throws IOException, ClassNotFoundException {
        while (isRun) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    MessageManager messageManager = new MessageManager(socketChannel);
                    messageManager.send(new Message(MessageType.REQUEST_NAME_USER));
                }
                if (key.isReadable()) {
                    interactionWithClients(key);
                }
                iter.remove();
            }
        }
    }

    public void stop() {
        try {
            if (isRun) {
                isRun = false;
                sendEachUser(new Message(MessageType.SERVER_STOP));
                for (Map.Entry<String, SelectionKey> user : users.entrySet()) {
                    user.getValue().channel().close();
                }
                serverSocketChannel.close();
            }
        } catch (IOException e) {
            viewServer.errorDialogWindow("Error of stopping server.");
        }
    }

    private void sendEachUser(Message message) throws IOException {
        for (Map.Entry<String, SelectionKey> user : users.entrySet()) {
            SocketChannel socketChannel = (SocketChannel) user.getValue().channel();
            MessageManager messageManager = new MessageManager(socketChannel);
            messageManager.send(message);
        }
    }

    private void interactionWithClients(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        MessageManager messageManager = new MessageManager(socketChannel);
        Message message = messageManager.receive();
        if (message.getMessageType() == MessageType.USER_NAME) {
            String name = message.getText();
            if (name != null && !name.isEmpty() && !users.containsKey(name)) {
                users.put(name, key);
                List<String> nameUsers = new ArrayList<>(users.keySet());
                messageManager.send(new Message(MessageType.NAME_ACCEPTED, nameUsers));
                sendEachUser(new Message(MessageType.USER_ADDED, name));
            } else {
                messageManager.send(new Message(MessageType.NAME_USED));
            }
        }  else if (message.getMessageType() == MessageType.TEXT_MESSAGE) {
            sendEachUser(new Message(MessageType.TEXT_MESSAGE, message.getText()));
        } else if (message.getMessageType() == MessageType.DISABLE_USER) {
            String name = message.getText();
            users.get(name).channel().close();
            users.remove(name);
            sendEachUser(new Message(MessageType.REMOVED_USER, name));
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        viewServer = new ViewServer(server);
        viewServer.displayFrame();
        server.start(6666);
    }

}
