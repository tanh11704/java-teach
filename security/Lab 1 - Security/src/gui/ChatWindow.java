package gui;

import service.ChatService;
import model.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class ChatWindow extends JFrame {
    private ChatService chatService;
    private JList<String> userList;
    private JList<String> groupList;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton fileButton;
    private JButton createGroupButton;
    private JButton joinGroupButton;
    private DefaultListModel<String> userListModel;
    private DefaultListModel<String> groupListModel;
    private String currentChat;
    private boolean isGroupChat;
    
    public ChatWindow(ChatService chatService) {
        this.chatService = chatService;
        setupUI();
        setupListeners();
    }
    
    private void setupUI() {
        setTitle("Chat Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        
        // User list
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        JScrollPane userScrollPane = new JScrollPane(userList);
        userScrollPane.setBorder(BorderFactory.createTitledBorder("Users"));
        
        // Group list
        groupListModel = new DefaultListModel<>();
        groupList = new JList<>(groupListModel);
        JScrollPane groupScrollPane = new JScrollPane(groupList);
        groupScrollPane.setBorder(BorderFactory.createTitledBorder("Groups"));
        
        // Combine user and group lists
        JSplitPane listSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userScrollPane, groupScrollPane);
        listSplitPane.setDividerLocation(300);
        leftPanel.add(listSplitPane, BorderLayout.CENTER);
        
        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        rightPanel.add(chatScrollPane, BorderLayout.CENTER);
        
        // Message input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Send");
        fileButton = new JButton("File");
        createGroupButton = new JButton("Create Group");
        joinGroupButton = new JButton("Join Group");
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(sendButton);
        buttonPanel.add(fileButton);
        buttonPanel.add(createGroupButton);
        buttonPanel.add(joinGroupButton);
        
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        rightPanel.add(inputPanel, BorderLayout.SOUTH);
        
        // Add panels to main frame
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private void setupListeners() {
        // User list selection
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    currentChat = selectedUser;
                    isGroupChat = false;
                    chatArea.setText("");
                }
            }
        });
        
        // Group list selection
        groupList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedGroup = groupList.getSelectedValue();
                if (selectedGroup != null) {
                    currentChat = selectedGroup;
                    isGroupChat = true;
                    chatArea.setText("");
                }
            }
        });
        
        // Send message
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
        
        // Send file
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (currentChat != null) {
                    chatService.sendFile(currentChat, selectedFile, isGroupChat);
                }
            }
        });
        
        // Create group
        createGroupButton.addActionListener(e -> {
            String groupName = JOptionPane.showInputDialog(this, "Enter group name:");
            if (groupName != null && !groupName.isEmpty()) {
                chatService.createGroup(groupName);
            }
        });
        
        // Join group
        joinGroupButton.addActionListener(e -> {
            String groupName = JOptionPane.showInputDialog(this, "Enter group name to join:");
            if (groupName != null && !groupName.isEmpty()) {
                chatService.joinGroup(groupName);
            }
        });
    }
    
    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty() && currentChat != null) {
            if (isGroupChat) {
                chatService.sendGroupMessage(currentChat, message);
            } else {
                chatService.sendChatMessage(currentChat, message);
            }
            messageField.setText("");
        }
    }
    
    public void updateUserList(List<String> users) {
        userListModel.clear();
        for (String user : users) {
            userListModel.addElement(user);
        }
    }
    
    public void updateGroupList(List<String> groups) {
        groupListModel.clear();
        for (String group : groups) {
            groupListModel.addElement(group);
        }
    }
    
    public void addMessage(Message message) {
        String sender = message.getSender();
        String content = message.getContent();
        String timestamp = message.getTimestamp();
        
        chatArea.append(String.format("[%s] %s: %s\n", timestamp, sender, content));
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
    
    public void addFileMessage(Message message) {
        String sender = message.getSender();
        String fileName = message.getFileName();
        String timestamp = message.getTimestamp();
        
        chatArea.append(String.format("[%s] %s sent a file: %s\n", timestamp, sender, fileName));
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
} 