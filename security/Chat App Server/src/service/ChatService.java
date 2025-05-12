package service;

import config.ServerConfig;
import controller.ChatController;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatService {
    private final ChatController chatController;
    private final ExecutorService executor;
    private final ServerConfig config;
    
    public ChatService() {
        this.config = new ServerConfig();
        this.chatController = new ChatController();
        this.executor = Executors.newCachedThreadPool();
    }
    
    public void handleNewClient(Socket clientSocket) {
        if (chatController.getConnectedUsers().size() >= config.getMaxConnections()) {
            System.out.println("Đã đạt số kết nối tối đa, từ chối client mới");
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Lỗi khi đóng socket client bị từ chối" + e);
            }
            return;
        }
        executor.submit(new ClientHandler(clientSocket, chatController));
    }
    
    public void shutdown() {
        executor.shutdown();
        System.out.println("Đã bắt đầu tắt dịch vụ trò chuyện");
    }
}
