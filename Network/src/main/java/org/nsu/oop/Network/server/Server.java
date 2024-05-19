package org.nsu.oop.Network.server;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageManager;
import org.nsu.oop.Network.communicate.MessageType;

import javax.xml.parsers.ParserConfigurationException;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.Logger;


public class Server {

    private static final Logger log = Logger.getLogger(Server.class.getName());

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private final Map<String, SelectionKey> users = new Hashtable<>();

    private final Map<SelectionKey, Boolean> protocols = new Hashtable<>();

    private static ViewServer viewServer;

    private boolean isRun;

    public void start(int port) {
        try {
            upServer(port);
            isRun = true;
            clientsProcessing();
        } catch (IOException | ClassNotFoundException | ParserConfigurationException e) {
            viewServer.errorDialogWindow("Error of working server.");
            log.warning("Error of working server.");
        }
    }

    private void upServer(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("Server is up!.");
    }

    private void clientsProcessing() throws IOException, ClassNotFoundException, ParserConfigurationException {
        while (isRun) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    connectingUser();
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
                log.info("Server is down!");
            }
        } catch (IOException | ParserConfigurationException e) {
            viewServer.errorDialogWindow("Error of stopping server.");
            log.warning("Error of stopping server.");
        }
    }

    private void sendEachUser(Message message) throws IOException, ParserConfigurationException {
        for (Map.Entry<String, SelectionKey> user : users.entrySet()) {
            SocketChannel socketChannel = (SocketChannel) user.getValue().channel();
            MessageManager messageManager = new MessageManager(socketChannel, protocols.get(user.getValue()));
            messageManager.send(message);
        }
    }

    private void connectingUser() throws IOException, ParserConfigurationException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
        ByteBuffer buffer = ByteBuffer.allocate(1);
        while (true) {
            if (socketChannel.read(buffer) == 1) {
                break;
            }
        }
        buffer.flip();
        boolean isXmlProtocol = buffer.get() == 1;
        protocols.put(key, isXmlProtocol);
        log.info("Client is connected. SelectionKey: " + key + ". Xml protocol: " + isXmlProtocol + ".");
        MessageManager messageManager = new MessageManager(socketChannel, isXmlProtocol);
        messageManager.send(new Message(MessageType.REQUEST_NAME_USER));
    }

    private void interactionWithClients(SelectionKey key) throws IOException, ClassNotFoundException, ParserConfigurationException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        MessageManager messageManager = new MessageManager(socketChannel, protocols.get(key));
        Message message = messageManager.receive();
        if (message.getMessageType() == MessageType.USER_NAME) {
            String name = message.getText();
            if (name != null && !name.isEmpty() && !users.containsKey(name)) {
                users.put(name, key);
                List<String> nameUsers = new ArrayList<>(users.keySet());
                messageManager.send(new Message(MessageType.NAME_ACCEPTED, nameUsers));
                sendEachUser(new Message(MessageType.USER_ADDED, name));
                log.info("Client logined. Name: " + name + ". SelectionKey: " + key + ".");
            } else {
                messageManager.send(new Message(MessageType.NAME_USED));
            }
        }  else if (message.getMessageType() == MessageType.TEXT_MESSAGE) {
            sendEachUser(new Message(MessageType.TEXT_MESSAGE, message.getText()));
            log.info(message.getText());
        } else if (message.getMessageType() == MessageType.DISABLE_USER) {
            String name = message.getText();
            SelectionKey keyDisabled = users.get(name);
            keyDisabled.channel().close();
            users.remove(name);
            protocols.remove(keyDisabled);
            log.info("User disabled. Name: " + name + ".");
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
