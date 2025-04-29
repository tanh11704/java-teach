package lab.jdbc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lab.jdbc.dao.DepartmentDAO;
import lab.jdbc.dao.StudentDAO;

public class StudentService {
    private static final int PORT = 8888;
    private ServerSocket serverSocket;
    private boolean running = false;
    private List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private StudentDAO studentDAO = new StudentDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
        
    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Server started on port " + PORT);
            
            new Thread(() -> {
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("New client connected: " + clientSocket.getInetAddress());
                        
                        ClientHandler handler = new ClientHandler(clientSocket, studentDAO, departmentDAO) {
                            @Override
                            protected void handleDisconnect() {
                                clients.remove(this);
                                System.out.println("Client handler removed from list");
                            }
                        };
                        
                        clients.add(handler);
                        new Thread(handler).start();
                    } catch (IOException e) {
                        if (running) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void stopServer() {
        running = false;
        for (ClientHandler client : clients) {
            client.closeConnection();
        }
        clients.clear();
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        StudentService server = new StudentService();
        server.startServer();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server...");
            server.stopServer();
        }));
    }
}
