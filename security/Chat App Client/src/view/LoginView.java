package view;

import config.ClientConfig;
import controller.ChatController;
import javax.swing.JOptionPane;

public class LoginView extends javax.swing.JFrame {
    private final ClientConfig config;

    public LoginView(ClientConfig config) {
        this.config = config;
        
        initComponents();
        
        serverField.setText(config.getServerHost());
        portField.setText(String.valueOf(config.getServerPort()));
    }
    
    private void handleConnect() {
        String username = usernameField.getText().trim();
        String server = serverField.getText().trim();
        String portText = portField.getText().trim();
        
        if (username.isEmpty()) {
            showError("Vui lòng nhập tên người dùng");
            return;
        }
        
        if (server.isEmpty()) {
            showError("Vui lòng nhập địa chỉ máy chủ");
            return;
        }
        
        int port;
        try {
            port = Integer.parseInt(portText);
            if (port < 1 || port > 65535) {
                showError("Cổng phải nằm trong khoảng từ 1 đến 65535");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Vui lòng nhập số cổng hợp lệ");
            return;
        }
        
        try {
            connectButton.setEnabled(false);
            connectButton.setText("Kết nối...");
            
            ChatController chatController = new ChatController(username, server, port);
            ChatView chatView = new ChatView(chatController);
            
            chatView.setVisible(true);
            dispose();
        } catch (Exception e) {
            connectButton.setEnabled(true);
            connectButton.setText("Kết nối");
            showError("Không kết nối được: " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        serverField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        connectButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secure Chat Login");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 250));

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new java.awt.BorderLayout(10, 10));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Secure Chat Application");
        headerPanel.add(jLabel1);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        formPanel.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        jLabel2.setText("Username:");
        formPanel.add(jLabel2);

        usernameField.setColumns(15);
        formPanel.add(usernameField);

        jLabel3.setText("Server:");
        formPanel.add(jLabel3);

        serverField.setColumns(15);
        formPanel.add(serverField);

        jLabel4.setText("Port:");
        formPanel.add(jLabel4);
        formPanel.add(portField);

        mainPanel.add(formPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(connectButton);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        handleConnect();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField portField;
    private javax.swing.JTextField serverField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
