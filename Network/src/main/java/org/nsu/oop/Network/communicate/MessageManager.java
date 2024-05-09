package org.nsu.oop.Network.communicate;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MessageManager {

    private final SocketChannel socketChannel;


    public MessageManager(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
    }

    public void send(Message message) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(baos)) {
            out.writeObject(message);
            byte[] msg = baos.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(msg);
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            };
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buffer);
        byte[] receivedBytes = new byte[bytesRead];
        buffer.flip();
        buffer.get(receivedBytes); // Копируем данные из буфера в массив байтов
        try (ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(receivedBytes))) {
            return (Message) in.readObject();
        }
    }


}