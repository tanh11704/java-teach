package controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import java.security.PublicKey;
import model.Message;
import model.User;
import security.CryptoUtils;
import security.KeyManager;
import security.SignatureVerifier;

public class ChatController {

    private final List<User> connectedUsers;
    private final KeyManager keyManager;
    private final SignatureVerifier signatureVerifier;

    public ChatController() {
        this.connectedUsers = new CopyOnWriteArrayList<>();
        this.keyManager = new KeyManager();
        this.signatureVerifier = new SignatureVerifier();

        try {
            keyManager.generateKeyPair();
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo Key Pairs: " + e);
        }
    }

    public void registerUser(User user) {
        connectedUsers.add(user);
        broadcastSystemMessage(user.getUsername() + " has joined the chat.");
        broadcastUserList();
        System.out.println("Người dùng đăng ký: " + user.getUsername());
    }

    public void removeUser(User user) {
        connectedUsers.remove(user);
        broadcastSystemMessage(user.getUsername() + " has left the chat.");
        broadcastUserList();
        System.out.println("Người dùng đăng xuất: " + user.getUsername());
    }

    public void broadcastMessage(Message message, User sender) {
        if (!verifyMessageSignature(message, sender)) {
            System.out.println("Xác minh chữ ký tin nhắn không thành công cho người dùng: " + sender.getUsername());
            return;
        }

        for (User user : connectedUsers) {
            if (!user.equals(sender)) {
                try {
                    byte[] encryptedContent = CryptoUtils.encrypt(
                            message.getContentAsBytes(),
                            user.getRSAPublicKey()
                    );

                    Message encryptedMessage = new Message(
                            sender.getUsername(),
                            encryptedContent,
                            message.getTimestamp(),
                            message.getSignature()
                    );

                    user.sendMessage(encryptedMessage);
                } catch (Exception e) {
                    System.out.println("Không gửi được tin nhắn cho người dùng: " + user.getUsername() + e);
                }
            }
        }
    }

    private void broadcastSystemMessage(String content) {
        Message systemMessage = new Message("Hệ thống", content, System.currentTimeMillis(), null);

        for (User user : connectedUsers) {
            try {
                user.sendMessage(systemMessage);
            } catch (Exception e) {
                System.out.println("Không gửi được tin nhắn hệ thống tới người dùng: " + user.getUsername() + e);
            }
        }
    }

    private void broadcastUserList() {
        List<String> usernames = new ArrayList<>();
        for (User user : connectedUsers) {
            usernames.add(user.getUsername());
        }

        Message userListMessage = new Message(
                "Hệ thống",
                "USER_LIST:" + String.join(",", usernames),
                System.currentTimeMillis(),
                null
        );

        for (User user : connectedUsers) {
            try {
                user.sendMessage(userListMessage);
            } catch (Exception e) {
                System.out.println("Failed to send user list to: " + user.getUsername() + e);
            }
        }
    }

    private boolean verifyMessageSignature(Message message, User sender) {
        if (message.getSignature() == null || "Hệ thống".equals(message.getSender())) {
            return true;
        }

        try {
            return signatureVerifier.verify(
                    message.getContentAsBytes(),
                    message.getSignature(),
                    sender.getDSAPublicKey()
            );
        } catch (Exception e) {
            System.out.println("Lỗi khi xác minh chữ ký tin nhắn" + e);
            return false;
        }
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public boolean verifyMessageSignature(Message message, PublicKey dsaPublicKey) {
        try {
            byte[] content = message.getContentAsBytes();
            byte[] signature = message.getSignature();
            return signatureVerifier.verify(content, signature, dsaPublicKey);
        } catch (Exception e) {
            System.out.println("Không thể xác minh chữ ký tin nhắn" + e);
            return false;
        }
    }
}
