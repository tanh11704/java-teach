package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    public enum Type {
        LOGIN,
        REGISTER,
        CHAT,
        GROUP_CHAT,
        FILE,
        GROUP_CREATE,
        GROUP_JOIN,
        GROUP_LEAVE,
        USER_LIST,
        GROUP_LIST
    }
    
    private Type type;
    private String sender;
    private String receiver;
    private String content;
    private String fileName;
    private byte[] fileData;
    private String timestamp;
    
    public Message(Type type, String sender, String receiver, String content) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public Message(Type type, String sender, String receiver, String fileName, byte[] fileData) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.fileName = fileName;
        this.fileData = fileData;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public Type getType() {
        return type;
    }
    
    public String getSender() {
        return sender;
    }
    
    public String getReceiver() {
        return receiver;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public byte[] getFileData() {
        return fileData;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
}
