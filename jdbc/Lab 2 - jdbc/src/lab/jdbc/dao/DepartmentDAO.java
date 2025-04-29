package lab.jdbc.dao;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import lab.jdbc.model.Department;

public class DepartmentDAO {
    private Connection connection;
    
    public DepartmentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL GetAllDepartments()}");
            ResultSet rs = cstmt.executeQuery();
            
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getString("ma_khoa"));
                department.setDepartmentName(rs.getString("ten_khoa"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return departments;
    }
    
    public Department getDepartmentById(String departmentId) {
        
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL GetDepartmentById(?)}");
            cstmt.setString(1, departmentId);
            ResultSet rs = cstmt.executeQuery();
            
            if (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getString("ma_khoa"));
                department.setDepartmentName(rs.getString("ten_khoa"));
                return department;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean addDepartment(Department department) {
        
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL AddDepartment(?, ?)}");
            cstmt.setString(1, department.getDepartmentId());
            cstmt.setString(2, department.getDepartmentName());
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateDepartment(Department department) {
        try{
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL UpdateDepartment(?, ?)}");
            cstmt.setString(1, department.getDepartmentId());
            cstmt.setString(2, department.getDepartmentName());
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteDepartment(String departmentId) {
        try {
            CallableStatement cstmt = (CallableStatement) connection.prepareCall("{CALL DeleteDepartment(?)}");
            cstmt.setString(1, departmentId);
            
            boolean result = cstmt.executeUpdate() > 0;
            cstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
