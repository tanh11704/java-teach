package service;

import model.Message;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatService {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String sessionToken;
    private ChatListener listener;
    private List<String> onlineUsers;
    private List<String> groups;
    
    public interface ChatListener {
        void onLoginSuccess(String sessionToken);
        void onLoginFailed();
        void onRegisterSuccess();
        void onRegisterFailed();
        void onMessageReceived(Message message);
        void onUserListUpdated(List<String> users);
        void onGroupListUpdated(List<String> groups);
    }
    
    public ChatService() {
        onlineUsers = new CopyOnWriteArrayList<>();
        groups = new CopyOnWriteArrayList<>();
        connect();
    }
    
    private void connect() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            // Start listening for messages
            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listenForMessages() {
        try {
            while (true) {
                Message message = (Message) in.readObject();
                handleMessage(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void handleMessage(Message message) {
        if (listener == null) return;
        
        switch (message.getType()) {
            case LOGIN:
                if (message.getContent().equals("SUCCESS")) {
                    sessionToken = message.getSender();
                    listener.onLoginSuccess(sessionToken);
                } else {
                    listener.onLoginFailed();
                }
                break;
                
            case REGISTER:
                if (message.getContent().equals("SUCCESS")) {
                    listener.onRegisterSuccess();
                } else {
                    listener.onRegisterFailed();
                }
                break;
                
            case USER_LIST:
                onlineUsers.clear();
                onlineUsers.addAll(List.of(message.getContent().split(",")));
                listener.onUserListUpdated(onlineUsers);
                break;
                
            case GROUP_LIST:
                groups.clear();
                groups.addAll(List.of(message.getContent().split(",")));
                listener.onGroupListUpdated(groups);
                break;
                
            default:
                listener.onMessageReceived(message);
                break;
        }
    }
    
    public void setChatListener(ChatListener listener) {
        this.listener = listener;
    }
    
    public void login(String username, String password) {
        try {
            Message message = new Message(Message.Type.LOGIN, username, "SERVER", password);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void register(String username, String password) {
        try {
            Message message = new Message(Message.Type.REGISTER, username, "SERVER", password);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendChatMessage(String receiver, String content) {
        try {
            Message message = new Message(Message.Type.CHAT, sessionToken, receiver, content);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendGroupMessage(String groupName, String content) {
        try {
            Message message = new Message(Message.Type.GROUP_CHAT, sessionToken, groupName, content);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendFile(String receiver, File file, boolean isGroup) {
        try {
            byte[] fileData = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileData);
            fis.close();
            
            Message message = new Message(
                isGroup ? Message.Type.GROUP_CHAT : Message.Type.CHAT,
                sessionToken,
                receiver,
                file.getName(),
                fileData
            );
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void createGroup(String groupName) {
        try {
            Message message = new Message(Message.Type.GROUP_CREATE, sessionToken, "SERVER", groupName);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void joinGroup(String groupName) {
        try {
            Message message = new Message(Message.Type.GROUP_JOIN, sessionToken, groupName, "");
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getOnlineUsers() {
        return new ArrayList<>(onlineUsers);
    }
    
    public List<String> getGroups() {
        return new ArrayList<>(groups);
    }
} 