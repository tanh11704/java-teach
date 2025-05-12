package view;

import components.ChatPanel;
import components.UserListPanel;
import controller.ChatController;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Message;

public class ChatView extends javax.swing.JFrame {
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
    private final ChatController chatController;
    private final ChatPanel chatPanel;
    private final UserListPanel userListPanel;

    public ChatView(ChatController chatController) {
        this.chatController = chatController;
        chatController.setChatView(this);
        
        chatPanel = new ChatPanel(message -> {
            if (!message.isEmpty()) {
                chatController.sendMessage(message);
            }
        });
        
        userListPanel = new UserListPanel();
        
        initComponents();
        
        splitPane.setLeftComponent(chatPanel);
        splitPane.setRightComponent(userListPanel);
        
        if (!chatController.connect()) {
            JOptionPane.showMessageDialog(this, 
                    "Không kết nối được với máy chủ. Vui lòng kiểm tra kết nối mạng của bạn và thử lại.",
                    "Lỗi kết nối", 
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        } else {
            setTitle("Secure Chat - " + chatController.getKeyManager().getRSAPublicKey().getAlgorithm() + " & " + 
                    chatController.getKeyManager().getDSAPublicKey().getAlgorithm() + " Encryption");
        }
    }
    
    public void displayMessage(Message message) {
        SwingUtilities.invokeLater(() -> {
            String time = TIME_FORMAT.format(new Date(message.getTimestamp()));
            String formattedMessage = String.format("[%s] %s: %s", 
                    time, message.getSender(), message.getContentAsString());
            
            chatPanel.addMessage(formattedMessage);
        });
    }
    
    public void displayErrorMessage(String errorMessage) {
        SwingUtilities.invokeLater(() -> {
            chatPanel.addErrorMessage(errorMessage);
        });
    }
    
    public void updateUserList(List<String> users) {
        SwingUtilities.invokeLater(() -> {
            userListPanel.updateUserList(users);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secure Chat");
        setMinimumSize(new Dimension(640, 480));
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        splitPane.setDividerLocation(600);
        splitPane.setOneTouchExpandable(true);
        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        chatController.disconnect();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
}
