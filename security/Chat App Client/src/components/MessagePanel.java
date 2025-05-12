package components;

import java.awt.Color;

public class MessagePanel extends javax.swing.JPanel {

    public MessagePanel(String sender, String content, String time, boolean isCurrentUser) {
        initComponents();
        
        setBackground(isCurrentUser ? new Color(220, 248, 198) : new Color(255, 255, 255));
        senderLabel.setText(sender);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        senderLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentArea = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout(5, 5));

        headerPanel.setOpaque(false);
        headerPanel.setLayout(new java.awt.BorderLayout());

        senderLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        senderLabel.setText("jLabel1");
        headerPanel.add(senderLabel, java.awt.BorderLayout.WEST);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel2.setForeground(java.awt.Color.gray);
        jLabel2.setText("jLabel2");
        headerPanel.add(jLabel2, java.awt.BorderLayout.EAST);

        add(headerPanel, java.awt.BorderLayout.NORTH);

        contentArea.setEditable(false);
        contentArea.setColumns(20);
        contentArea.setLineWrap(true);
        contentArea.setRows(5);
        contentArea.setWrapStyleWord(true);
        contentArea.setBorder(null);
        contentArea.setOpaque(false);
        jScrollPane1.setViewportView(contentArea);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea contentArea;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel senderLabel;
    // End of variables declaration//GEN-END:variables
}
