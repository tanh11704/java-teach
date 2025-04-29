import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    ServerSocket serverSocket;
    Integer port;
    List<ClientHandler> clientHandlers = new ArrayList<>();

    public Server(Integer port) {
        this.port = port;
    }

    public void start() throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is running on port: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket);
            clientHandlers.add(handler);
            new Thread(handler).start();
        }
    }
}
