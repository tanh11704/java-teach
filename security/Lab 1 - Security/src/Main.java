package lab.pkg1.security;

import service.ChatService;
import gui.LoginDialog;
import gui.ChatWindow;
import model.Message;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create chat service
        ChatService chatService = new ChatService();
        
        // Create and show login dialog
        LoginDialog loginDialog = new LoginDialog(null, chatService);
        loginDialog.setVisible(true);
        
        // If login successful, show chat window
        if (loginDialog.isLoginSuccess()) {
            ChatWindow chatWindow = new ChatWindow(chatService);
            chatWindow.setVisible(true);
            
            // Set up chat service listener
            chatService.setChatListener(new ChatService.ChatListener() {
                @Override
                public void onLoginSuccess(String sessionToken) {
                    loginDialog.onLoginSuccess(sessionToken);
                }
                
                @Override
                public void onLoginFailed() {
                    loginDialog.onLoginFailed();
                }
                
                @Override
                public void onRegisterSuccess() {
                    loginDialog.onRegisterSuccess();
                }
                
                @Override
                public void onRegisterFailed() {
                    loginDialog.onRegisterFailed();
                }
                
                @Override
                public void onMessageReceived(Message message) {
                    if (message.getType() == Message.Type.FILE) {
                        chatWindow.addFileMessage(message);
                    } else {
                        chatWindow.addMessage(message);
                    }
                }
                
                @Override
                public void onUserListUpdated(List<String> users) {
                    chatWindow.updateUserList(users);
                }
                
                @Override
                public void onGroupListUpdated(List<String> groups) {
                    chatWindow.updateGroupList(groups);
                }
            });
        }
    }
} 