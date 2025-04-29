package lab.jdbc.ui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import lab.jdbc.model.Department;
import lab.jdbc.model.Student;

public class StudentManagementFrame extends javax.swing.JFrame {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<Department> departments;
    private List<Student> students;

    public StudentManagementFrame() {
        initComponents();
        init();
        connectToServer();
        
        loadStudents();
        loadDepartments();
    }
    
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 8888);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Cannot connect to server: " + e.getMessage(), 
                "Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void init() {
       studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
                int row = studentTable.getSelectedRow();
                if (row >= 0) {
                    idField.setText((String) tableModel.getValueAt(row, 0));
                    nameField.setText((String) tableModel.getValueAt(row, 1));
                    String departmentName = (String) tableModel.getValueAt(row, 2);
                    for (int i = 0; i < departmentComboBox.getItemCount(); i++) {
                        Department dept = departmentComboBox.getItemAt(i);
                        if (dept.getDepartmentName().equals(departmentName)) {
                            departmentComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                    idField.setEnabled(false);
                }
            }
        });
    }
    
    private void loadStudents() {
        try {
            output.writeObject("GET_ALL_STUDENTS");
            output.flush();
            students = (List<Student>) input.readObject();
            DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
            tableModel.setRowCount(0);

            for (Student student : students) {
                Vector<Object> row = new Vector<>();
                row.add(student.getStudentId());
                row.add(student.getName());
                row.add(student.getDepartmentName());
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading students: " + e.getMessage(), 
                "Data Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void loadDepartments() {
        try {
            departmentComboBox.removeAllItems();
            output.writeObject("GET_ALL_DEPARTMENTS");
            output.flush();
            departments = (List<Department>) input.readObject();
            for (Department department : departments) {
                departmentComboBox.addItem(department);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading departments: " + e.getMessage(), 
                "Data Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        southPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        formPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STUDENT MANAGEMENT");
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        titlePanel.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("STUDENT MANAGEMENT");
        titlePanel.add(titleLabel, java.awt.BorderLayout.CENTER);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stu ID", "Name", "Department"
            }
        ));
        studentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        studentTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(studentTable);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        southPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        southPanel.setLayout(new java.awt.BorderLayout(10, 10));

        buttonPanel.setLayout(new java.awt.GridLayout(2, 3, 10, 10));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(addButton);

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(editButton);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(deleteButton);

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(searchButton);

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(clearButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        southPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        formPanel.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        jLabel1.setText("Stu ID:");
        formPanel.add(jLabel1);

        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });
        formPanel.add(idField);

        jLabel2.setText("Name:");
        formPanel.add(jLabel2);
        formPanel.add(nameField);

        jLabel3.setText("Department:");
        formPanel.add(jLabel3);

        formPanel.add(departmentComboBox);

        southPanel.add(formPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();
        
        if (id.isEmpty() || name.isEmpty() || selectedDepartment == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", 
                                         "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Student student = new Student(id, name, selectedDepartment.getDepartmentId(), selectedDepartment.getDepartmentName());
        
        try {
            output.writeObject("ADD_STUDENT");
            output.flush();
            output.writeObject(student);
            output.flush();
            
            Boolean success = (Boolean) input.readObject();
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
                loadStudents();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại!", 
                                         "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();
        
        if (id.isEmpty() || name.isEmpty() || selectedDepartment == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", 
                                         "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Student student = new Student(id, name, selectedDepartment.getDepartmentId(), selectedDepartment.getDepartmentName());
        
        try {
            output.writeObject("UPDATE_STUDENT");
            output.flush();
            output.writeObject(student);
            output.flush();
            
            Boolean success = (Boolean) input.readObject();
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công!");
                loadStudents();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thất bại!", 
                                         "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String id = idField.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần xóa!", 
                                         "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                                                   "Bạn có chắc chắn muốn xóa sinh viên này?", 
                                                   "Xác nhận", 
                                                   JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                output.writeObject("DELETE_STUDENT");
                output.flush();
                output.writeObject(id);
                output.flush();

                Boolean success = (Boolean) input.readObject();

                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công!");
                    loadStudents();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sinh viên thất bại!", 
                                             "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String id = idField.getText().trim();
        
        if (id.isEmpty()) {
            loadStudents();
            return;
        }
        
        try {
            output.writeObject("SEARCH_STUDENT");
            output.flush();
            students = (List<Student>) input.readObject();
            DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
            tableModel.setRowCount(0);

            for (Student student : students) {
                Vector<Object> row = new Vector<>();
                row.add(student.getStudentId());
                row.add(student.getName());
                row.add(student.getDepartmentName());
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading students: " + e.getMessage(), 
                "Data Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearForm();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        departmentComboBox.setSelectedIndex(0);
        idField.setEnabled(true);
        studentTable.clearSelection();
        loadStudents();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<Department> departmentComboBox;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel formPanel;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nameField;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel southPanel;
    private javax.swing.JTable studentTable;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

}
