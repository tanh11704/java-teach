package model;

import java.io.Serializable;
import java.security.Timestamp;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String displayName;
    private String status;
    private Timestamp createdAt;
    
    public User() {
    }

    public User(int id, String username, String password, String displayName, String status, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
