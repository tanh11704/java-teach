import config.ServerConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import security.KeyManager;
import service.ChatService;

public class ServerApplication {
    private ServerSocket serverSocket;
    private final ChatService chatService;
    private final KeyManager keyManager;
    private final ServerConfig config;
    private boolean running = false;
        
    public ServerApplication() {
        this.config = new ServerConfig();
        this.keyManager = new KeyManager();
        this.chatService = new ChatService();
        
        initializeServer(config.getPort());
        generateServerKeys();
    }
    
    private void initializeServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout((int) config.getTimeout()); // Đặt thời gian chờ cho socket
            System.out.println("Server được khởi tạo trên cổng " + port + " với thời gian chờ " + config.getTimeout());
        } catch (IOException e) {
            System.out.println("Không thể khởi tạo socket server" + e);
            System.exit(1);
        }
    }
    
    private void generateServerKeys() {
        try {
            keyManager.generateKeyPair();
            System.out.println("Khóa máy chủ đã được tạo thành công");
        } catch (Exception e) {
            System.out.println("Không tạo được khóa máy chủ" + e);
            System.exit(1);
        }
    }
    
    public void start() {
        running = true;
        System.out.println("Máy chủ đã khởi động. Đang chờ kết nối của máy khách...");
        
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Đã kết nối máy khách mới: " + clientSocket.getInetAddress().getHostAddress());
                chatService.handleNewClient(clientSocket);
            } catch (IOException e) {
                if (running) {
                    System.out.println("Lỗi khi chấp nhận kết nối máy khách" + e);
                }
            }
        }
    }
    
    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            chatService.shutdown();
            System.out.println("Máy chủ đã dừng");
        } catch (IOException e) {
            System.out.println("Lỗi khi đóng ổ cắm máy chủ" + e);
        }
    }
    
    public static void main(String[] args) {
        ServerApplication server = new ServerApplication();
        
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        
        server.start();
    }
}
