package components;

import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import swing.UserCellRenderer;

public class UserListPanel extends javax.swing.JPanel {

    private final DefaultListModel<String> userListModel;

    public UserListPanel() {
        userListModel = new DefaultListModel<>();
        initComponents();
    }

    public void updateUserList(List<String> users) {
        userListModel.clear();

        for (String user : users) {
            userListModel.addElement(user);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 10));
        setPreferredSize(new Dimension(180, 0));
        setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setText("Online Users");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jList1.setModel(userListModel);
        jList1.setCellRenderer(new UserCellRenderer());
        scrollPane.setViewportView(jList1);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
