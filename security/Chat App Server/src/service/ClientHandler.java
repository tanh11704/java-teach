package service;

import controller.ChatController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import model.Message;
import model.User;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ChatController chatController;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private User user;
    
    public ClientHandler(Socket clientSocket, ChatController chatController) {
        this.clientSocket = clientSocket;
        this.chatController = chatController;
    }

    @Override
    public void run() {
        try {
            setupStreams();
            performHandshake();
            processMessages();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi trong trình xử lý máy khách" + e);
        } finally {
            cleanup();
        }
    }
    
    private void setupStreams() throws IOException {
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        outputStream.flush();
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }
    
    private void performHandshake() throws IOException, ClassNotFoundException {
        outputStream.writeObject(chatController.getKeyManager().getRSAPublicKey());
        outputStream.writeObject(chatController.getKeyManager().getDSAPublicKey());
        outputStream.flush();
        
        String username = (String) inputStream.readObject();
        PublicKey clientRSAPublicKey = (PublicKey) inputStream.readObject();
        PublicKey clientDSAPublicKey = (PublicKey) inputStream.readObject();
        
        // Create user and register with chat controller
        user = new User(username, clientSocket, outputStream, clientRSAPublicKey, clientDSAPublicKey);
        chatController.registerUser(user);
        
        System.out.println("Hoàn tất handsnake cho người dùng: " + username);
    }
    
    private void processMessages() throws IOException, ClassNotFoundException {
        while (!clientSocket.isClosed()) {
            try {
                Message message = (Message) inputStream.readObject();
                System.out.println("Nhận tin nhắn từ:  " + user.getUsername());
                
                if (chatController.verifyMessageSignature(message, user.getDSAPublicKey())) {
                    chatController.broadcastMessage(message, user);
                } else {
                    System.out.println("Xác minh chữ ký tin nhắn không thành công đối với người dùng: " + user.getUsername());
                }
            } catch (IOException | ClassNotFoundException e) {
                if (!clientSocket.isClosed()) {
                    throw e;
                }
                break;
            }
        }
    }
    
    private void cleanup() {
        if (user != null) {
            chatController.removeUser(user);
            user.disconnect();
        }
        
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException e) {
            System.out.println("Lỗi trong quá trình dọn dẹp" + e);
        }
    }
}
