package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Message;
import model.SinhVien;

public class SinhVienController {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public SinhVienController() {
        connectToServer();
    }
    
    private void connectToServer() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Kết nối đến server thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<SinhVien> getAllSinhVien() {
        try {
            Message request = new Message(Message.GET_ALL, null);
            out.writeObject(request);
            out.flush();
            
            Message response = (Message) in.readObject();
            if (response.isStatus()) {
                return (List<SinhVien>) response.getData();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public boolean addSinhVien(SinhVien sv) {
        try {
            Message request = new Message(Message.ADD, sv);
            out.writeObject(request);
            out.flush();
            
            Message response = (Message) in.readObject();
            return response.isStatus();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateSinhVien(SinhVien sv) {
        try {
            Message request = new Message(Message.UPDATE, sv);
            out.writeObject(request);
            out.flush();
            
            Message response = (Message) in.readObject();
            return response.isStatus();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteSinhVien(String stuID) {
        try {
            Message request = new Message(Message.DELETE, stuID);
            out.writeObject(request);
            out.flush();
            
            Message response = (Message) in.readObject();
            return response.isStatus();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public SinhVien findSinhVien(String stuID) {
        try {
            Message request = new Message(Message.FIND, stuID);
            out.writeObject(request);
            out.flush();
            
            Message response = (Message) in.readObject();
            if (response.isStatus()) {
                return (SinhVien) response.getData();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
