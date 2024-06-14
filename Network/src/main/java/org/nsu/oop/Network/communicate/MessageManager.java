package org.nsu.oop.Network.communicate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private final SocketChannel socketChannel;

    private final Boolean isXmlProtocol;


    public MessageManager(SocketChannel socketChannel, boolean isXmlProtocol) throws IOException {
        this.socketChannel = socketChannel;
        this.isXmlProtocol = isXmlProtocol;
    }

    public void send(Message message) throws IOException, ParserConfigurationException {
        if (isXmlProtocol) {
            Document document = createXmlDocument(message);
            String msg = convertDocumentToString(document);
            int len = msg.length();
            sendXml(len, msg);
        } else {
            sendSerialize(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException, ParserConfigurationException {
        if (isXmlProtocol) {
            Document document = receiveXml();
            return convertDocumentToMessage(document);
        } else {
            return receiveDeserialize();
        }
    }

    private Document createXmlDocument(Message message) throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("message");
        Element type = document.createElement("type");
        type.setTextContent(message.getMessageType().toString());
        root.appendChild(type);
        String text = message.getText();
        if (text != null) {
            Element elementText = document.createElement("text");
            elementText.setTextContent(text);
            root.appendChild(elementText);
        }
        List<String> nameUsers = message.getNameUsers();
        if (nameUsers != null) {
            for (String nameUser : nameUsers) {
                Element user = document.createElement("user");
                user.setTextContent(nameUser);
                root.appendChild(user);
            }
        }
        document.appendChild(root);
        return document;
    }

    public static String convertDocumentToString(Document document) throws IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new IOException();
        }
    }

    private void sendSerialize(Message message) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(baos)) {
            out.writeObject(message);
            byte[] msg = baos.toByteArray();
            int len = msg.length;
            ByteBuffer buffer = ByteBuffer.allocate(4 + len);
            buffer.putInt(len);
            buffer.put(msg);
            buffer.flip();
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        }
    }

    private void sendXml(int len, String xml) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4 + len);
        buffer.putInt(len);
        buffer.put(xml.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    private Document receiveXml() throws IOException, ParserConfigurationException {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            int receivedBytes = 0;
            do {
                receivedBytes += socketChannel.read(buffer);
            } while (receivedBytes != 4);
            buffer.flip();
            int size = buffer.getInt();
            ByteBuffer bufferForXml = ByteBuffer.allocate(size);
            receivedBytes = 0;
            do {
                receivedBytes += socketChannel.read(bufferForXml);
            } while (receivedBytes != size);
            bufferForXml.flip();
            byte[] stringByte = new byte[size];
            bufferForXml.get(stringByte);
            String xml = new String(stringByte, StandardCharsets.UTF_8);
            InputSource inputSource = new InputSource(new StringReader(xml));
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
        } catch (SAXException e) {
            throw new IOException();
        }
    }

    private Message receiveDeserialize() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        int receivedBytes = 0;
        do {
            receivedBytes += socketChannel.read(buffer);
        } while (receivedBytes != 4);
        buffer.flip();
        int size = buffer.getInt();
        ByteBuffer bufferForMessage = ByteBuffer.allocate(size);
        receivedBytes = 0;
        do {
            receivedBytes += socketChannel.read(bufferForMessage);
        } while (receivedBytes != size);
        bufferForMessage.flip();
        byte[] seqBytes = new byte[size];
        bufferForMessage.get(seqBytes);
        try (ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(seqBytes))) {
            return (Message) in.readObject();
        }
    }

    private Message convertDocumentToMessage(Document document) {
        MessageType messageType = MessageType.valueOf(document.getDocumentElement().getElementsByTagName("type").item(0).getTextContent());
        String text;
        NodeList nodeListText = document.getDocumentElement().getElementsByTagName("text");
        if (nodeListText.getLength() == 0) {
            text = null;
        } else {
            text = nodeListText.item(0).getTextContent();
        }
        List<String> nameUsers;
        NodeList nodeListUsers = document.getDocumentElement().getElementsByTagName("user");
        if (nodeListUsers.getLength() == 0) {
            nameUsers = null;
        } else {
            nameUsers = new ArrayList<>();
            for (int i = 0; i < nodeListUsers.getLength(); i++) {
                nameUsers.add(nodeListUsers.item(i).getTextContent());
            }
        }
        return new Message(messageType, text, nameUsers);
    }



}