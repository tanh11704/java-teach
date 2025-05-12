package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Group;

public class GroupDAO {
    private final Connection CONNECTION;

    public GroupDAO(Connection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }
    
    public boolean createGroup(Group group) throws SQLException {
        String sql = "INSERT INTO groups (group_name, created_by, created_at) VALUES (?, ?, ?)";
        try (
            PreparedStatement pstmt = CONNECTION.prepareStatement(sql)
        ) {
            pstmt.setString(1, group.getGroupName());
            pstmt.setInt(2, group.getCreatedBy());
            pstmt.setTimestamp(3, group.getCreatedAt());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }
    
    
}
