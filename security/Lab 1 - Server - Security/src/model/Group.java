package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Group implements Serializable {
    private int id;
    private String groupName;
    private int createdBy;
    private Timestamp createdAt;

    public Group() {
    }

    public Group(int id, String groupName, int createdBy, Timestamp createdAt) {
        this.id = id;
        this.groupName = groupName;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
