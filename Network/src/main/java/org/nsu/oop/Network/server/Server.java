package org.nsu.oop.Network.server;

import java.net.*;
import java.io.*;

public class Server {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new Thread(new ClientHandler(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.err.println("IOException server.");
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if ("aboba".equals(inputLine)) {
                        System.out.println("work");
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException client handler.");
            }
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }

}
