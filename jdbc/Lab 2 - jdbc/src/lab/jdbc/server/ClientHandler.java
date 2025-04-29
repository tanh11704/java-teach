package lab.jdbc.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import lab.jdbc.dao.DepartmentDAO;
import lab.jdbc.dao.StudentDAO;
import lab.jdbc.model.Department;
import lab.jdbc.model.Student;

public abstract class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean connected = true;
    private StudentDAO studentDAO;
    private DepartmentDAO departmentDAO;
    
    public ClientHandler(Socket socket, StudentDAO studentDAO, DepartmentDAO departmentDAO) {
        this.clientSocket = socket;
        this.studentDAO = studentDAO;
        this.departmentDAO = departmentDAO;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }
    }

    @Override
    public void run() {
        try {
            while (connected) {
                String command = (String) input.readObject();
                processCommand(command);
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            handleDisconnect();
        }
    }
    
    protected abstract void handleDisconnect();
    
    private void processCommand(String command) throws IOException, ClassNotFoundException {
        System.out.println("Received command: " + command);

        switch (command) {
            case "GET_ALL_STUDENTS":
                List<Student> students = studentDAO.getAllStudents();
                output.writeObject(students);
                output.flush();
                break;

            case "GET_ALL_DEPARTMENTS":
                List<Department> departments = departmentDAO.getAllDepartments();
                output.writeObject(departments);
                output.flush();
                break;

            case "ADD_STUDENT":
                Student newStudent = (Student) input.readObject();
                boolean addSuccess = studentDAO.addStudent(newStudent);
                output.writeObject(addSuccess);
                output.flush();
                break;

            case "UPDATE_STUDENT":
                Student updatedStudent = (Student) input.readObject();
                boolean updateSuccess = studentDAO.updateStudent(updatedStudent);
                output.writeObject(updateSuccess);
                output.flush();
                break;

            case "DELETE_STUDENT":
                String studentID = (String) input.readObject();
                boolean deleteSuccess = studentDAO.deleteStudent(studentID);
                output.writeObject(deleteSuccess);
                output.flush();
                break;

            case "SEARCH_STUDENT":
                String searchID = (String) input.readObject();
                List<Student> foundStudent = studentDAO.searchStudentsByID(searchID);
                output.writeObject(foundStudent);
                output.flush();
                break;

            default:
                output.writeObject("Unknown command");
                output.flush();
                break;
        }
    }

    public void closeConnection() {
        connected = false;
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
