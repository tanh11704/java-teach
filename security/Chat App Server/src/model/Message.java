package model;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String sender;
    private final Object content;
    private final long timestamp;
    private final byte[] signature;
    
    public Message(String sender, Object content, long timestamp, byte[] signature) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
        this.signature = signature;
    }
    
    public String getSender() {
        return sender;
    }
    
    public Object getContent() {
        return content;
    }
    
    public String getContentAsString() {
        if (content instanceof String) {
            return (String) content;
        }
        return new String((byte[]) content);
    }
    
    public byte[] getContentAsBytes() {
        if (content instanceof byte[]) {
            return (byte[]) content;
        }
        return ((String) content).getBytes();
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public byte[] getSignature() {
        return signature;
    }
    
    @Override
    public String toString() {
        return String.format("[%s]: %s", sender, 
                content instanceof String ? content : "[Encrypted Content]");
    }
}
