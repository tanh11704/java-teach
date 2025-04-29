import java.io.*;
import java.net.*;
import java.util.*;

public class Peer {
    private Server server;
    private List<Client> clients = new ArrayList<>();
    private final int port;

    public Peer(int port) {
        this.port = port;
        this.server = new Server(port);
    }

    // Khởi động server
    public void startServer() {
        new Thread(() -> {
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Kết nối đến một peer khác
    public void connectToPeer(String address, int port) {
        Client client = new Client(address, port);
        clients.add(client);
        client.connect();
    }

    // Gửi tin nhắn đến tất cả các peers đã kết nối
    public void sendMessage(String message) {
        for (Client client : clients) {
            client.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        Peer peer = new Peer(5000);
        peer.startServer();

        // Kết nối đến peer khác (có thể thay đổi địa chỉ IP và port)
        peer.connectToPeer("localhost", 1234);

        // Gửi tin nhắn
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            peer.sendMessage(message);
        }
    }
}
