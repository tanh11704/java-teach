package model;

public class Message {
    private String content;
    private String sender;
    private boolean fromCurrentUser;
    private String time;

    public Message(String content, String sender, boolean fromCurrentUser, String time) {
        this.content = content;
        this.sender = sender;
        this.fromCurrentUser = fromCurrentUser;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public boolean isFromCurrentUser() {
        return fromCurrentUser;
    }

    public String getTime() {
        return time;
    }    
    
}
