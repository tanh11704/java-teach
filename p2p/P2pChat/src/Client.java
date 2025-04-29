import java.io.*;
import java.net.*;

public class Client {
    private final String address;
    private final int port;
    private Socket socket;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    // Kết nối đến peer khác
    public void connect() {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to peer: " + address + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gửi tin nhắn đến peer khác
    public void sendMessage(String message) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
