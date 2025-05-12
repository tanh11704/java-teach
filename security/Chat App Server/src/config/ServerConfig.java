package config;

public class ServerConfig {
    private static final int DEFAULT_PORT = 8888;
    private static final int MAX_CONNECTIONS = 100;
    private static final long TIMEOUT = 60000;
    
    public int getPort() {
        return DEFAULT_PORT;
    }
    
    public int getMaxConnections() {
        return MAX_CONNECTIONS;
    }
    
    public long getTimeout() {
        return TIMEOUT;
    }
}
