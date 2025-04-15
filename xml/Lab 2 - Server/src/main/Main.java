package main;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import model.SinhVienModel;

public class Main {
    private SinhVienModel model;
    private static final int PORT = 8888;
    
    public Main() {
        model = new SinhVienModel();
        startServer();
    }
    
    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server đang chạy tại cổng " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client đã kết nối: " + clientSocket.getInetAddress());
                
                ClientHandler handler = new ClientHandler(clientSocket, model);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
