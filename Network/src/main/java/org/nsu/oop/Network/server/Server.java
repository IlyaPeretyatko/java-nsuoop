package org.nsu.oop.Network.server;

import org.nsu.oop.Network.communicate.Message;
import org.nsu.oop.Network.communicate.MessageManager;
import org.nsu.oop.Network.communicate.MessageType;

import java.net.*;
import java.io.*;
import java.util.*;


public class Server {

    private ServerSocket serverSocket;

    private final Map<String, MessageManager> users = new Hashtable<>();

    List<Thread> handlers = new ArrayList<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Thread handler = new Thread(new ClientHandler(serverSocket.accept()));
            handlers.add(handler);
            handler.start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        for (Thread handler : handlers) {
            handler.interrupt();
        }
    }

    private void sendEachUser(Message message) throws IOException {
        for (Map.Entry<String, MessageManager> user : users.entrySet()) {
            MessageManager messageManager = user.getValue();
            messageManager.send(message);
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        private String requestUserName(MessageManager messageManager) throws IOException, ClassNotFoundException {
            while (true) {
                messageManager.send(new Message(MessageType.REQUEST_NAME_USER));
                Message answer = messageManager.receive();
                String name = answer.getText();
                if (name != null && !name.isEmpty() && !users.containsKey(name) && answer.getMessageType() == MessageType.USER_NAME) {
                    users.put(name, messageManager);
                    List<String> nameUsers = new ArrayList<>(users.keySet());
                    messageManager.send(new Message(MessageType.NAME_ACCEPTED, nameUsers));
                    sendEachUser(new Message(MessageType.USER_ADDED, name));
                    return name;
                } else {
                    messageManager.send(new Message(MessageType.NAME_USED));
                }
            }
        }

        private void messaging(MessageManager messageManager, String name) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = messageManager.receive();
                if (message.getMessageType() == MessageType.TEXT_MESSAGE) {
                    String msg = name + ": " + message.getText();
                    sendEachUser(new Message(MessageType.TEXT_MESSAGE, msg));
                } else if (message.getMessageType() == MessageType.DISABLE_USER) {
                    sendEachUser(new Message(MessageType.REMOVED_USER, name));
                    users.remove(name);
                    break;
                }
            }
        }

        @Override
        public void run() {
            try (MessageManager messageManager = new MessageManager(clientSocket)) {
                String name = requestUserName(messageManager);
                messaging(messageManager, name);
            } catch (IOException e) {
                System.err.println("IOException client handler: run.");
            } catch (ClassNotFoundException e) {
                System.err.println("ClassNotFoundException client handler: request user name.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
        server.stop();
    }

}
