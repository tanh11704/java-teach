package dao;

import model.User;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class UserDao {
    private final Connection CONNECTION;

    public UserDao(Connection CONNECTION) {
        this.CONNECTION = CONNECTION;
    }
    
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, display_name) VALUES (?, ?, ?)";
        try (
            PreparedStatement pstmt = CONNECTION.prepareStatement(sql)
        ) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getDisplayName());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        }
    }
    
    public boolean authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement pstmt = CONNECTION.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return BCrypt.checkpw(password, rs.getString("password"));
                }
            }
            return false;
        }
    }
    
    public void updateStatus(String username, String status) throws SQLException {
        String sql = "UPDATE users SET status = ? WHERE username = ?";
        try (PreparedStatement pstmt = CONNECTION.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }
    }
    
    public int getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement pstmt = CONNECTION.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("id") : -1;
            }
        }
    }

    public JSONArray getContactList() throws SQLException {
        JSONArray contacts = new JSONArray();
        String sql = "SELECT username, display_name, status FROM users";
        try (Statement stmt = CONNECTION.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JSONObject contact = new JSONObject();
                contact.put("username", rs.getString("username"));
                contact.put("displayName", rs.getString("display_name"));
                contact.put("status", rs.getString("status"));
                contacts.put(contact);
            }
        }
        return contacts;
    }
}
