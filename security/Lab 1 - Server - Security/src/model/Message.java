package model;

import java.io.Serializable;
import java.security.Timestamp;

public class Message implements Serializable { 
    private int id;
    private int senderId;
    private Integer receiverId;  // Can be null if it's a group message
    private Integer groupId;     // Can be null if it's a direct message
    private String messageText;
    private Timestamp sentAt;

    public Message() {
    }

    public Message(int id, int senderId, Integer receiverId, Integer groupId, String messageText, Timestamp sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.groupId = groupId;
        this.messageText = messageText;
        this.sentAt = sentAt;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isGroupMessage() {
        return groupId != null;
    }

    public boolean isDirectMessage() {
        return receiverId != null;
    }
}