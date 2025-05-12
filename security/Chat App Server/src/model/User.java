package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;

public class User {
    private final String username;
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final PublicKey rsaPublicKey;
    private final PublicKey dsaPublicKey;
    
    public User(String username, Socket socket, ObjectOutputStream outputStream, PublicKey rsaPublicKey, PublicKey dsaPublicKey) {
        this.username = username;
        this.socket = socket;
        this.outputStream = outputStream;
        this.rsaPublicKey = rsaPublicKey;
        this.dsaPublicKey = dsaPublicKey;
    }
    
    public String getUsername() {
        return username;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public PublicKey getRSAPublicKey() {
        return rsaPublicKey;
    }
    
    public PublicKey getDSAPublicKey() {
        return dsaPublicKey;
    }
    
    public void sendMessage(Message message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Failed to send message to user: " + username + e);
        }
    }
    
    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing socket for user: " + username + e);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        User other = (User) obj;
        return username.equals(other.username);
    }
    
    @Override
    public int hashCode() {
        return username.hashCode();
    }
    
}
