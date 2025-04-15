package model;

import java.io.Serializable;

public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static final int GET_ALL = 1;
    public static final int ADD = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;
    public static final int FIND = 5;
    
    private int type;
    private Object data;
    private boolean status;
    private String message;
    
    public Message() {
    }
    
    public Message(int type, Object data) {
        this.type = type;
        this.data = data;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
