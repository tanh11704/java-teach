package components;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ChatPanel extends javax.swing.JPanel {

    private final Consumer<String> sendMessageCallback;

    public ChatPanel(Consumer<String> sendMessageCallback) {
        this.sendMessageCallback = sendMessageCallback;

        initComponents();
    }

    private void sendMessage() {
        String message = messageArea.getText().trim();
        if (!message.isEmpty()) {
            sendMessageCallback.accept(message);
            messageArea.setText("");
        }
        messageArea.requestFocusInWindow();
    }

    public void addMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = chatArea.getStyledDocument();
            try {
                Style style = chatArea.addStyle("MessageStyle", null);
                StyleConstants.setForeground(style, Color.BLACK);

                doc.insertString(doc.getLength(), message + "\n", style);
                chatArea.setCaretPosition(doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    public void addErrorMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = chatArea.getStyledDocument();
            try {
                Style style = chatArea.addStyle("ErrorStyle", null);
                StyleConstants.setForeground(style, Color.RED);

                doc.insertString(doc.getLength(), "ERROR: " + message + "\n", style);
                chatArea.setCaretPosition(doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatScrollPane = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextPane();
        bottomPanel = new javax.swing.JPanel();
        messageScrollPane = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout(5, 5));

        chatScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        chatArea.setEditable(false);
        chatArea.setBackground(new java.awt.Color(250, 250, 250));
        chatScrollPane.setViewportView(chatArea);

        add(chatScrollPane, java.awt.BorderLayout.CENTER);

        bottomPanel.setLayout(new java.awt.BorderLayout(5, 0));

        messageArea.setColumns(20);
        messageArea.setLineWrap(true);
        messageArea.setRows(3);
        messageArea.setWrapStyleWord(true);
        messageScrollPane.setViewportView(messageArea);

        bottomPanel.add(messageScrollPane, java.awt.BorderLayout.CENTER);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        sendButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendButtonKeyPressed(evt);
            }
        });
        bottomPanel.add(sendButton, java.awt.BorderLayout.EAST);

        add(bottomPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !evt.isShiftDown()) {
            evt.consume();
            sendMessage();
        }
    }//GEN-LAST:event_sendButtonKeyPressed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JTextPane chatArea;
    private javax.swing.JScrollPane chatScrollPane;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JScrollPane messageScrollPane;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
