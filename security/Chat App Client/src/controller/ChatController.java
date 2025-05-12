package controller;

import java.util.List;
import java.util.ArrayList;
import model.Message;
import security.KeyManager;
import service.ChatService;
import view.ChatView;

public class ChatController {
    private final ChatService chatService;
    private final KeyManager keyManager;
    private ChatView chatView;
    private final List<String> onlineUsers;
    
    public ChatController(String username, String serverHost, int serverPort) {
        this.keyManager = new KeyManager();
        this.chatService = new ChatService(this, username, serverHost, serverPort, keyManager);
        this.onlineUsers = new ArrayList<>();
    }
    
    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }
    
    public boolean connect() {
        try {
            keyManager.generateKeyPairs();
            return chatService.connect();
        } catch (Exception e) {
            System.out.println("Failed to connect to server" + e);
            return false;
        }
    }
    
    public void disconnect() {
        chatService.disconnect();
    }
    
    public void sendMessage(String content) {
        try {
            long timestamp = System.currentTimeMillis();
            
            byte[] signature = keyManager.sign(content.getBytes());
            
            Message message = new Message(chatService.getUsername(), content, timestamp, signature);
            
            chatService.sendMessage(message);
            
            chatView.displayMessage(message);
        } catch (Exception e) {
            System.out.println("Không gửi được tin nhắn" + e);
            chatView.displayErrorMessage("Không gửi được tin nhắn: " + e.getMessage());
        }
    }
    
    public void receiveMessage(Message message) {
        try {
            if (message.getContent() instanceof byte[]) {
                byte[] decryptedContent = keyManager.decrypt((byte[]) message.getContent());
                String decryptedText = new String(decryptedContent);
                
                Message decryptedMessage = new Message(
                        message.getSender(),
                        decryptedText,
                        message.getTimestamp(),
                        message.getSignature()
                );
                
                chatView.displayMessage(decryptedMessage);
            } else {
                String content = message.getContentAsString();
                if ("Hệ thống".equals(message.getSender())) {
                    if (content.startsWith("USER_LIST:")) {
                        String[] usernames = content.substring("USER_LIST:".length()).split(",");
                        onlineUsers.clear();
                        for (String username : usernames) {
                            if (!username.isEmpty()) {
                                onlineUsers.add(username);
                            }
                        }
                        chatView.updateUserList(onlineUsers);
                    } else {
                        chatView.displayMessage(message);
                    }
                } else {
                    chatView.displayMessage(message);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to process received message" + e);
        }
    }
    
    public void handleUserJoined(String username) {
        if (!onlineUsers.contains(username)) {
            onlineUsers.add(username);
            chatView.updateUserList(onlineUsers);
        }
    }
    
    public void handleUserLeft(String username) {
        onlineUsers.remove(username);
        chatView.updateUserList(onlineUsers);
    }
    
    public List<String> getOnlineUsers() {
        return onlineUsers;
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
}
