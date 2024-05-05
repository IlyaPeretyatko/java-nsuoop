package org.nsu.oop.Network.communicate;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private MessageType messageType;
    private String text;
    private List<String> nameUsers;

    public Message(MessageType messageType) {
        this.messageType = messageType;
        this.text = null;
        this.nameUsers = null;
    }

    public Message(MessageType messageType, String text) {
        this.messageType = messageType;
        this.text = text;
        this.nameUsers = null;
    }


    public Message(MessageType messageType, List<String> nameUsers) {
        this.messageType = messageType;
        this.nameUsers = nameUsers;
        this.text = null;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getText() {
        return text;
    }
}
