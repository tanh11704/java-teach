package main;


import model.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.SinhVien;
import model.SinhVienModel;

public class ClientHandler implements Runnable{
    private SinhVienModel model;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket, SinhVienModel model) {
        this.clientSocket = socket;
        this.model = model;
//        model = new SinhVienModel();
        
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message request = (Message) in.readObject();
                Message response = processRequest(request);

                out.writeObject(response);
                out.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client ngắt kết nối: " + clientSocket.getInetAddress());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Message processRequest(Message request) {
        Message response = new Message();
        response.setType(request.getType());

        switch (request.getType()) {
            case Message.GET_ALL:
                List<SinhVien> dsSinhVien = model.getDanhSachSinhVien();
                response.setData(new ArrayList<>(dsSinhVien));
                response.setStatus(true);
                response.setMessage("Lấy danh sách sinh viên thành công");
                break;

            case Message.ADD:
                SinhVien svAdd = (SinhVien) request.getData();
                boolean addSuccess = model.addSinhVien(svAdd);
                response.setStatus(addSuccess);
                if (addSuccess) {
                    response.setMessage("Thêm sinh viên thành công");
                } else {
                    response.setMessage("ID sinh viên đã tồn tại");
                }
                break;

            case Message.UPDATE:
                SinhVien svUpdate = (SinhVien) request.getData();
                boolean updateSuccess = model.updateSinhVien(svUpdate);
                response.setStatus(updateSuccess);
                if (updateSuccess) {
                    response.setMessage("Cập nhật sinh viên thành công");
                } else {
                    response.setMessage("Không tìm thấy sinh viên để cập nhật");
                }
                break;

            case Message.DELETE:
                String stuID = (String) request.getData();
                boolean deleteSuccess = model.deleteSinhVien(stuID);
                response.setStatus(deleteSuccess);
                if (deleteSuccess) {
                    response.setMessage("Xóa sinh viên thành công");
                } else {
                    response.setMessage("Không tìm thấy sinh viên để xóa");
                }
                break;

            case Message.FIND:
                String findID = (String) request.getData();
                SinhVien svFind = model.findSinhVien(findID);
                response.setData(svFind);
                response.setStatus(svFind != null);
                if (svFind != null) {
                    response.setMessage("Tìm thấy sinh viên");
                } else {
                    response.setMessage("Không tìm thấy sinh viên");
                }
                break;
        }

        return response;
    }
}
