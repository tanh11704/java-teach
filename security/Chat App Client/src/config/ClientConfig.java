package config;

public class ClientConfig {    
    private static final String DEFAULT_SERVER = "localhost";
    private static final int DEFAULT_PORT = 8888;
    
    
    public ClientConfig() {
        System.out.println("Cấu hình client được khởi tạo");
    }
    
    public String getServerHost() {
        return DEFAULT_SERVER;
    }
    
    public int getServerPort() {
        return DEFAULT_PORT;
    }
}
