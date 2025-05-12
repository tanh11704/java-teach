package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import model.Message;

public class MessageListener implements Runnable {
    
    private final ChatService chatService;
    private final ObjectInputStream inputStream;
    private boolean running = false;
    
    public MessageListener(ChatService chatService, ObjectInputStream inputStream) {
        this.chatService = chatService;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        running = true;
        
        while (running) {
            try {
                Message message = (Message) inputStream.readObject();
                System.out.println("Đã nhận được tin nhắn từ: " + message.getSender());
                
                chatService.receiveMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                if (running) {
                    System.out.println("Lỗi khi nhận tin nhắn" + e);
                    chatService.disconnect();
                }
                break;
            }
        }
    }
    
    public void stop() {
        running = false;
    }
}
