package service;

import controller.ChatController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import model.Message;
import security.KeyManager;

public class ChatService {
    private final ChatController chatController;
    private final String username;
    private final String serverHost;
    private final int serverPort;
    private final KeyManager keyManager;
    
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private MessageListener messageListener;
    private boolean connected = false;
    
    public ChatService(ChatController chatController, String username, String serverHost, int serverPort, KeyManager keyManager) {
        this.chatController = chatController;
        this.username = username;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.keyManager = keyManager;
    }
    
    public boolean connect() {
        try {
            socket = new Socket(serverHost, serverPort);
            
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            
            performHandshake();
            
            messageListener = new MessageListener(this, inputStream);
            new Thread(messageListener).start();
            
            connected = true;
            System.out.println("Đã kết nối tới máy chủ: " + serverHost + ":" + serverPort);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Không thể kết nối tới máy chủ" + e);
            cleanup();
            return false;
        }
    }
    
    private void performHandshake() throws IOException, ClassNotFoundException {
        PublicKey serverRSAPublicKey = (PublicKey) inputStream.readObject();
        PublicKey serverDSAPublicKey = (PublicKey) inputStream.readObject();
        keyManager.setServerPublicKeys(serverRSAPublicKey, serverDSAPublicKey);
        
        outputStream.writeObject(username);
        outputStream.writeObject(keyManager.getRSAPublicKey());
        outputStream.writeObject(keyManager.getDSAPublicKey());
        outputStream.flush();
        
        System.out.println("Bắt tay hoàn tất với máy chủ");
    }
    
    public void sendMessage(Message message) {
        try {
            if (connected && outputStream != null) {
                outputStream.writeObject(message);
                outputStream.flush();
                System.out.println("Tin nhắn được gửi đến máy chủ");
            } else {
                System.out.println("Không thể gửi tin nhắn, không kết nối được với máy chủ");
            }
        } catch (IOException e) {
            System.out.println("Không gửi được tin nhắn" + e);
            disconnect();
        }
    }
    
    public void receiveMessage(Message message) {
        chatController.receiveMessage(message);
    }
    
    public void disconnect() {
        if (messageListener != null) {
            messageListener.stop();
        }
        
        cleanup();
        connected = false;
        System.out.println("Đã ngắt kết nối khỏi máy chủ");
    }
    
    private void cleanup() {
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Lỗi trong quá trình dọn dẹp" + e);
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean isConnected() {
        return connected;
    }
}
