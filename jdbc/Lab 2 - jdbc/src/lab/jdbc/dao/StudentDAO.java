package lab.jdbc.dao;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lab.jdbc.model.Student;

public class StudentDAO {
    private Connection connection;
    
    public StudentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL GetAllStudents()}");
            ResultSet rs = cstmt.executeQuery();
                    
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("ma_sinh_vien"));
                student.setName(rs.getString("ho_ten"));
                student.setDepartmentId(rs.getString("ma_khoa"));
                student.setDepartmentName(rs.getString("ten_khoa"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }
    
    public Student getStudentById(String studentId) {
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL GetStudentByID(?)}");
            cstmt.setString(1, studentId);
            ResultSet rs = cstmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("ma_sinh_vien"));
                student.setName(rs.getString("ho_ten"));
                student.setDepartmentId(rs.getString("ma_khoa"));
                student.setDepartmentName(rs.getString("ten_khoa"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean addStudent(Student student) {
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL AddStudent(?, ?, ?)}");
            cstmt.setString(1, student.getStudentId());
            cstmt.setString(2, student.getName());
            cstmt.setString(3, student.getDepartmentId());
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStudent(Student student) {
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL UpdateStudent(?, ?, ?)}");
            cstmt.setString(1, student.getStudentId());
            cstmt.setString(2, student.getName());
            cstmt.setString(3, student.getDepartmentId());
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteStudent(String studentId) {
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL DeleteStudent(?)}");
            cstmt.setString(1, studentId);
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Student> searchStudentsByID(String studentId) {
        List<Student> students = new ArrayList<>();
        
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL SearchStudentsByID(?)}");
            cstmt.setString(1, studentId);
            ResultSet rs = cstmt.executeQuery();
            
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("ma_sinh_vien"));
                student.setName(rs.getString("ho_ten"));
                student.setDepartmentId(rs.getString("ma_khoa"));
                student.setDepartmentName(rs.getString("ten_khoa"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }
}