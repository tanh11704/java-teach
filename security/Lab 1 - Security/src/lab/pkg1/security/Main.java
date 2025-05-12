package lab.pkg1.security;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Message;
import swing.ContactListCellRenderer;
import swing.RoundedBorder;
import utils.Constants;
import java.awt.CardLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends javax.swing.JFrame {
    
    private DefaultListModel<String> contactsModel;
    private DefaultListModel<String> groupsModel;
    private Map<String, java.util.List<Message>> conversations;
    private CardLayout cardLayout;

    public Main() {
        initComponents();
        init();
        initializeData();
        contactsList.setModel(contactsModel);
        groupsList.setModel(groupsModel);
        cardLayout = (CardLayout) cardsPanel.getLayout();
    }
    
    private void init() {
        messageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        detailScroll.getVerticalScrollBar().setUnitIncrement(16);
    }

    private void initializeData() {
        contactsModel = new DefaultListModel<>();
        contactsModel.addElement("Alice Smith");
        contactsModel.addElement("Bob Johnson");
        contactsModel.addElement("Carol Williams");
        contactsModel.addElement("David Brown");
        contactsModel.addElement("Emma Davis");
        
        groupsModel = new DefaultListModel<>();
        groupsModel.addElement("Team Project");
        groupsModel.addElement("Family Group");
        groupsModel.addElement("Friends Chat");
        
        conversations = new HashMap<>();
        
        // Add some sample messages
        java.util.List<Message> aliceMessages = new ArrayList<>();
        aliceMessages.add(new Message("Hi there! How are you?", "Alice Smith", false, "10:30 AM"));
        aliceMessages.add(new Message("I'm doing great, thanks for asking!", "You", true, "10:32 AM"));
        aliceMessages.add(new Message("What are your plans for the weekend?", "Alice Smith", false, "10:33 AM"));
        conversations.put("Alice Smith", aliceMessages);
        
        java.util.List<Message> teamMessages = new ArrayList<>();
        teamMessages.add(new Message("Hey team, let's discuss the project timeline", "David Brown", false, "Yesterday"));
        teamMessages.add(new Message("I think we should extend the deadline", "Carol Williams", false, "Yesterday"));
        teamMessages.add(new Message("I agree with Carol", "You", true, "Yesterday"));
        teamMessages.add(new Message("Let's schedule a meeting tomorrow", "Emma Davis", false, "Today"));
        conversations.put("Team Project", teamMessages);
    }
    
    private void showLoginDialog() {
        loginDialog = new LoginDialog(this, client);
        loginDialog.setVisible(true);
        
        if (!loginDialog.isLoginSuccess()) {
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new javax.swing.JPanel();
        sidebarHeader = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        newChatButton = new javax.swing.JButton();
        newGroupButton = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        tabbedPane = new javax.swing.JTabbedPane();
        contactsScroll = new javax.swing.JScrollPane();
        contactsList = new javax.swing.JList<>();
        groupsScroll = new javax.swing.JScrollPane();
        groupsList = new javax.swing.JList<>();
        contentPanel = new javax.swing.JPanel();
        cardsPanel = new javax.swing.JPanel();
        welcomePanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        chatWithMessages = new javax.swing.JPanel();
        chatHeader = new javax.swing.JPanel();
        chatTitleLabel = new javax.swing.JLabel();
        chatHeaderButtons = new javax.swing.JPanel();
        videoCallButton = new javax.swing.JButton();
        audioCallButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        messageScrollPane = new javax.swing.JScrollPane();
        messagesContainer = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();
        messageField = new javax.swing.JTextField();
        buttonsPanel = new javax.swing.JPanel();
        buttonsPanelLeft = new javax.swing.JPanel();
        attachButton = new javax.swing.JButton();
        buttonsPanelRight = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();
        detailPanel = new javax.swing.JPanel();
        detailHeader = new javax.swing.JPanel();
        detailTitleLabel = new javax.swing.JLabel();
        closeDetailButton = new javax.swing.JButton();
        detailScroll = new javax.swing.JScrollPane();
        detailContent = new javax.swing.JPanel();
        profilePanel = new javax.swing.JPanel();
        profilePic = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        actionPanel = new javax.swing.JPanel();
        messageButton = new javax.swing.JButton();
        callButton = new javax.swing.JButton();
        videoButton = new javax.swing.JButton();
        muteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(1000, 700));

        sidebarPanel.setBackground(Constants.MAC_SIDEBAR);
        sidebarPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, Constants.MAC_BORDER));
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        sidebarPanel.setLayout(new java.awt.BorderLayout());

        sidebarHeader.setBackground(Constants.MAC_SIDEBAR);
        sidebarHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        sidebarHeader.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        titleLabel.setText("Messages");
        sidebarHeader.add(titleLabel, java.awt.BorderLayout.WEST);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        newChatButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        newChatButton.setText("➕");
        newChatButton.setToolTipText("New Chat");
        newChatButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        newChatButton.setContentAreaFilled(false);
        newChatButton.setFocusPainted(false);
        buttonPanel.add(newChatButton);

        newGroupButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        newGroupButton.setText("👥");
        newGroupButton.setToolTipText("New Group");
        newGroupButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        newGroupButton.setContentAreaFilled(false);
        newGroupButton.setFocusPainted(false);
        buttonPanel.add(newGroupButton);

        sidebarHeader.add(buttonPanel, java.awt.BorderLayout.EAST);

        sidebarPanel.add(sidebarHeader, java.awt.BorderLayout.NORTH);

        searchPanel.setBackground(Constants.MAC_SIDEBAR);
        searchPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 15, 15));
        searchPanel.setLayout(new java.awt.BorderLayout());

        searchField.setBackground(new java.awt.Color(245, 245, 245));
        searchField.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        searchField.setForeground(java.awt.Color.gray);
        searchField.setText("Search");
        searchField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.MAC_BORDER), javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchPanel.add(searchField, java.awt.BorderLayout.CENTER);

        sidebarPanel.add(searchPanel, java.awt.BorderLayout.SOUTH);

        tabbedPane.setFont(new Font("SF Pro Text", Font.PLAIN, 13));

        contactsScroll.setBorder(null);

        contactsList.setModel(new javax.swing.DefaultListModel<>());
        contactsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        contactsList.setCellRenderer(new ContactListCellRenderer(false));
        contactsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                contactsListValueChanged(evt);
            }
        });
        contactsScroll.setViewportView(contactsList);

        tabbedPane.addTab("Contact", contactsScroll);

        groupsScroll.setBorder(null);

        groupsList.setBorder(null);
        groupsList.setModel(new javax.swing.DefaultListModel<>());
        groupsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        groupsList.setCellRenderer(new ContactListCellRenderer(true));
        groupsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                groupsListValueChanged(evt);
            }
        });
        groupsScroll.setViewportView(groupsList);

        tabbedPane.addTab("Group", groupsScroll);

        sidebarPanel.add(tabbedPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(sidebarPanel, java.awt.BorderLayout.WEST);

        contentPanel.setBackground(Constants.MAC_BACKGROUND);
        contentPanel.setLayout(new java.awt.BorderLayout());

        cardsPanel.setLayout(new java.awt.CardLayout());

        welcomePanel.setBackground(Constants.MAC_BACKGROUND);
        welcomePanel.setLayout(new java.awt.GridBagLayout());

        welcomeLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 16));
        welcomeLabel.setForeground(Constants.MAC_GREY_TEXT);
        welcomeLabel.setText("Select a conversation or start a new one");
        welcomePanel.add(welcomeLabel, new java.awt.GridBagConstraints());

        cardsPanel.add(welcomePanel, "welcome");

        chatWithMessages.setLayout(new java.awt.BorderLayout());

        chatHeader.setBackground(Constants.MAC_BACKGROUND);
        chatHeader.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.MAC_BORDER), javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        chatHeader.setLayout(new java.awt.BorderLayout());

        chatTitleLabel.setFont(new Font("SF Pro Display", Font.BOLD, 15));
        chatTitleLabel.setText("Alice Smith");
        chatHeader.add(chatTitleLabel, java.awt.BorderLayout.WEST);

        chatHeaderButtons.setOpaque(false);
        chatHeaderButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        videoCallButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        videoCallButton.setText("📹");
        videoCallButton.setToolTipText("Video Call");
        videoCallButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        videoCallButton.setContentAreaFilled(false);
        videoCallButton.setFocusPainted(false);
        chatHeaderButtons.add(videoCallButton);

        audioCallButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        audioCallButton.setText("📞");
        audioCallButton.setToolTipText("Audio Call");
        audioCallButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        audioCallButton.setContentAreaFilled(false);
        audioCallButton.setFocusPainted(false);
        chatHeaderButtons.add(audioCallButton);

        infoButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        infoButton.setText("ℹ");
        infoButton.setToolTipText("Info");
        infoButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        infoButton.setContentAreaFilled(false);
        infoButton.setFocusPainted(false);
        chatHeaderButtons.add(infoButton);

        chatHeader.add(chatHeaderButtons, java.awt.BorderLayout.EAST);

        chatWithMessages.add(chatHeader, java.awt.BorderLayout.CENTER);

        messagesContainer.setBackground(Constants.MAC_BACKGROUND);
        messagesContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagesContainer.setLayout(new javax.swing.BoxLayout(messagesContainer, javax.swing.BoxLayout.Y_AXIS));
        messageScrollPane.setViewportView(messagesContainer);

        chatWithMessages.add(messageScrollPane, java.awt.BorderLayout.CENTER);

        inputPanel.setBackground(Constants.MAC_BACKGROUND);
        inputPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 20, 20));
        inputPanel.setLayout(new java.awt.BorderLayout());

        messageField.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        messageField.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(Constants.MAC_BORDER, 1, true), javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        messageField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageFieldActionPerformed(evt);
            }
        });
        inputPanel.add(messageField, java.awt.BorderLayout.CENTER);

        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new java.awt.BorderLayout());

        buttonsPanelLeft.setOpaque(false);
        buttonsPanelLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        attachButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        attachButton.setText("📎");
        attachButton.setToolTipText("Attach");
        attachButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        attachButton.setContentAreaFilled(false);
        attachButton.setFocusPainted(false);
        buttonsPanelLeft.add(attachButton);

        buttonsPanel.add(buttonsPanelLeft, java.awt.BorderLayout.WEST);

        buttonsPanelRight.setOpaque(false);
        buttonsPanelRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        sendButton.setBackground(Constants.MAC_BLUE);
        sendButton.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("Send");
        sendButton.setBorder(new RoundedBorder(Constants.MAC_BLUE, 5));
        sendButton.setFocusPainted(false);
        sendButton.setMaximumSize(new Dimension(80, 30));
        sendButton.setMinimumSize(new Dimension(80, 30));
        sendButton.setPreferredSize(new Dimension(80, 30));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        buttonsPanelRight.add(sendButton);

        buttonsPanel.add(buttonsPanelRight, java.awt.BorderLayout.EAST);

        inputPanel.add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        chatWithMessages.add(inputPanel, java.awt.BorderLayout.SOUTH);

        cardsPanel.add(chatWithMessages, "card2");

        contentPanel.add(cardsPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        detailPanel.setBackground(Constants.MAC_BACKGROUND);
        detailPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, Constants.MAC_BORDER));
        detailPanel.setPreferredSize(new Dimension(250, getHeight()));
        detailPanel.setLayout(new java.awt.BorderLayout());

        detailHeader.setBackground(Constants.MAC_BACKGROUND);
        detailHeader.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.MAC_BORDER), javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        detailHeader.setLayout(new java.awt.BorderLayout());

        detailTitleLabel.setFont(new Font("SF Pro Display", Font.BOLD, 15));
        detailTitleLabel.setText("Details");
        detailHeader.add(detailTitleLabel, java.awt.BorderLayout.WEST);

        closeDetailButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        closeDetailButton.setText("✕");
        closeDetailButton.setToolTipText("Close");
        closeDetailButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeDetailButton.setContentAreaFilled(false);
        closeDetailButton.setFocusPainted(false);
        detailHeader.add(closeDetailButton, java.awt.BorderLayout.EAST);

        detailPanel.add(detailHeader, java.awt.BorderLayout.NORTH);

        detailScroll.setBorder(null);

        detailContent.setBackground(Constants.MAC_BACKGROUND);
        detailContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 20, 15));
        detailContent.setLayout(new javax.swing.BoxLayout(detailContent, javax.swing.BoxLayout.Y_AXIS));

        profilePanel.setOpaque(false);

        profilePic.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        profilePic.setText("👤");
        profilePanel.add(profilePic);

        detailContent.add(profilePanel);

        infoPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 20, 0));
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new javax.swing.BoxLayout(infoPanel, javax.swing.BoxLayout.Y_AXIS));

        nameLabel.setFont(new Font("SF Pro Display", Font.BOLD, 16));
        nameLabel.setText("Alice Smith");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nameLabel);

        statusLabel.setForeground(Constants.MAC_GREY_TEXT);
        statusLabel.setText("Online");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(statusLabel);

        detailContent.add(infoPanel);

        actionPanel.setOpaque(false);
        actionPanel.setLayout(new javax.swing.BoxLayout(actionPanel, javax.swing.BoxLayout.Y_AXIS));

        messageButton.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        messageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chat.png"))); // NOI18N
        messageButton.setText("Message");
        messageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(new RoundedBorder(Constants.MAC_BORDER, 5), javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        messageButton.setFocusPainted(false);
        messageButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        messageButton.setMaximumSize(new Dimension(200, 36));
        messageButton.setMinimumSize(new Dimension(200, 36));
        messageButton.setPreferredSize(new Dimension(200, 36));
        actionPanel.add(messageButton);

        callButton.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        callButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/phone.png"))); // NOI18N
        callButton.setText("Call");
        callButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        callButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(new RoundedBorder(Constants.MAC_BORDER, 5), javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        callButton.setFocusPainted(false);
        callButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        callButton.setMaximumSize(new Dimension(200, 36));
        callButton.setMinimumSize(new Dimension(200, 36));
        callButton.setPreferredSize(new Dimension(200, 36));
        actionPanel.add(callButton);

        videoButton.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        videoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/camera.png"))); // NOI18N
        videoButton.setText("Video");
        videoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        videoButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(new RoundedBorder(Constants.MAC_BORDER, 5), javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        videoButton.setFocusPainted(false);
        videoButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        videoButton.setMaximumSize(new Dimension(200, 36));
        videoButton.setMinimumSize(new Dimension(200, 36));
        videoButton.setPreferredSize(new Dimension(200, 36));
        actionPanel.add(videoButton);

        muteButton.setFont(new Font("SF Pro Text", Font.PLAIN, 13));
        muteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/silent.png"))); // NOI18N
        muteButton.setText("Mute");
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        muteButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(new RoundedBorder(Constants.MAC_BORDER, 5), javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        muteButton.setFocusPainted(false);
        muteButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        muteButton.setMaximumSize(new Dimension(200, 36));
        muteButton.setMinimumSize(new Dimension(200, 36));
        muteButton.setPreferredSize(new Dimension(200, 36));
        actionPanel.add(muteButton);

        detailContent.add(actionPanel);

        detailScroll.setViewportView(detailContent);

        detailPanel.add(detailScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(detailPanel, java.awt.BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        if (searchField.getText().equals("Search")) {
            searchField.setText("");
            searchField.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_searchFieldFocusGained

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if (searchField.getText().isEmpty()) {
            searchField.setForeground(Color.GRAY);
            searchField.setText("Search");
        }
    }//GEN-LAST:event_searchFieldFocusLost

    private void contactsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_contactsListValueChanged
        if (!evt.getValueIsAdjusting()) {
            String selectedContact = contactsList.getSelectedValue();
            if (selectedContact != null) {
                // Clear group selection
                groupsList.clearSelection();
                displayMessages(selectedContact);
                cardLayout.show(cardsPanel, "chat");
            }
        }
    }//GEN-LAST:event_contactsListValueChanged

    private void groupsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_groupsListValueChanged
        if (!evt.getValueIsAdjusting()) {
            String selectedGroup = groupsList.getSelectedValue();
            if (selectedGroup != null) {
                // Clear contact selection
                contactsList.clearSelection();
                displayMessages(selectedGroup);
                cardLayout.show(cardsPanel, "chat");
            }
        }
    }//GEN-LAST:event_groupsListValueChanged

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String messageText = messageField.getText().trim();
        if (!messageText.isEmpty()) {
            String selectedContact = contactsList.getSelectedValue();
            String selectedGroup = groupsList.getSelectedValue();
            String currentConversation = selectedContact != null ? selectedContact : selectedGroup;

            if (currentConversation != null) {
                if (selectedContact != null) {
                    client.sendChatMessage(messageText, selectedContact);
                } else {
                    client.sendGroupMessage(messageText, selectedGroup);
                }
                
                // Clear message field
                messageField.setText("");
            }
        }
    }
    
    private void messageFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageFieldActionPerformed
        String messageText = messageField.getText().trim();
        if (!messageText.isEmpty()) {
            String selectedContact = contactsList.getSelectedValue();
            String selectedGroup = groupsList.getSelectedValue();
            String currentConversation = selectedContact != null ? selectedContact : selectedGroup;

            if (currentConversation != null) {
                // Get current time
                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                String currentTime = timeFormat.format(new Date());

                // Create and add new message
                Message newMessage = new Message(messageText, "You", true, currentTime);
                if (!conversations.containsKey(currentConversation)) {
                    conversations.put(currentConversation, new ArrayList<>());
                }
                conversations.get(currentConversation).add(newMessage);

                // Display updated messages
                displayMessages(currentConversation);

                // Clear message field
                messageField.setText("");
            }
        }
    }//GEN-LAST:event_messageFieldActionPerformed

    private void displayMessages(String conversation) {
        messagesContainer.removeAll();
        
        if (conversations.containsKey(conversation)) {
            java.util.List<Message> messages = conversations.get(conversation);
            
            for (Message message : messages) {
                JPanel messagePanel = createMessagePanel(message);
                messagesContainer.add(messagePanel);
                messagesContainer.add(Box.createVerticalStrut(10));
            }
            
            // Add glue at the end to push messages to the top
            messagesContainer.add(Box.createVerticalGlue());
        }
        
        messagesContainer.revalidate();
        messagesContainer.repaint();
        
        // Scroll to bottom
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = messageScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }
    
    private JPanel createMessagePanel(Message message) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        
        // Message content
        JTextArea textArea = new JTextArea(message.getContent());
        textArea.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        
        // Styling based on sender
        if (message.isFromCurrentUser()) {
            textArea.setBackground(Constants.MAC_BLUE);
            textArea.setForeground(Color.WHITE);
            panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        } else {
            textArea.setBackground(new Color(240, 240, 240));
            textArea.setForeground(Color.BLACK);
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        
        // Add padding and round corners
        textArea.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(textArea);
        textPanel.setBorder(new RoundedBorder(textArea.getBackground(), 15));
        textPanel.setOpaque(false);
        
        // Time label
        JLabel timeLabel = new JLabel(message.getTime());
        timeLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 11));
        timeLabel.setForeground(Constants.MAC_GREY_TEXT);
        
        // Name label for group chats
        JLabel nameLabel = null;
        if (!message.isFromCurrentUser() && message.getSender() != null && !message.getSender().isEmpty()) {
            nameLabel = new JLabel(message.getSender());
            nameLabel.setFont(new Font("SF Pro Text", Font.BOLD, 12));
            nameLabel.setForeground(Constants.MAC_GREY_TEXT);
        }
        
        // Layout components based on sender
        if (message.isFromCurrentUser()) {
            panel.add(textPanel, BorderLayout.CENTER);
            
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            infoPanel.setOpaque(false);
            infoPanel.add(timeLabel);
            panel.add(infoPanel, BorderLayout.SOUTH);
            
            // Add some margin on the left to make it right-aligned
            panel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        } else {
            panel.add(textPanel, BorderLayout.CENTER);
            
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setOpaque(false);
            if (nameLabel != null) {
                infoPanel.add(nameLabel);
                infoPanel.add(Box.createHorizontalStrut(10));
            }
            infoPanel.add(timeLabel);
            panel.add(infoPanel, BorderLayout.SOUTH);
            
            // Add some margin on the right to make it left-aligned
            panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));
        }
        
        return panel;
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton attachButton;
    private javax.swing.JButton audioCallButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel buttonsPanelLeft;
    private javax.swing.JPanel buttonsPanelRight;
    private javax.swing.JButton callButton;
    private javax.swing.JPanel cardsPanel;
    private javax.swing.JPanel chatHeader;
    private javax.swing.JPanel chatHeaderButtons;
    private javax.swing.JLabel chatTitleLabel;
    private javax.swing.JPanel chatWithMessages;
    private javax.swing.JButton closeDetailButton;
    private javax.swing.JList<String> contactsList;
    private javax.swing.JScrollPane contactsScroll;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel detailContent;
    private javax.swing.JPanel detailHeader;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JScrollPane detailScroll;
    private javax.swing.JLabel detailTitleLabel;
    private javax.swing.JList<String> groupsList;
    private javax.swing.JScrollPane groupsScroll;
    private javax.swing.JButton infoButton;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JButton messageButton;
    private javax.swing.JTextField messageField;
    private javax.swing.JScrollPane messageScrollPane;
    private javax.swing.JPanel messagesContainer;
    private javax.swing.JButton muteButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton newChatButton;
    private javax.swing.JButton newGroupButton;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePic;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JButton sendButton;
    private javax.swing.JPanel sidebarHeader;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton videoButton;
    private javax.swing.JButton videoCallButton;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables

    public void displayMessage(Message message) {
        String conversation = message.getRecipient();
        if (!conversations.containsKey(conversation)) {
            conversations.put(conversation, new ArrayList<>());
        }
        conversations.get(conversation).add(message);
        
        // Update UI if this is the current conversation
        String selectedContact = contactsList.getSelectedValue();
        String selectedGroup = groupsList.getSelectedValue();
        String currentConversation = selectedContact != null ? selectedContact : selectedGroup;
        
        if (conversation.equals(currentConversation)) {
            displayMessages(conversation);
        }
    }
    
    public void handleFileMessage(Message message) {
        // Handle incoming file
        String fileName = message.getFileName();
        byte[] fileData = message.getFileData();
        
        // Show file save dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(fileName));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                fos.write(fileData);
                JOptionPane.showMessageDialog(this,
                    "File saved successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "Error saving file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void onLoginSuccess() {
        loginDialog.onLoginSuccess();
    }
    
    public void onLoginFailed() {
        loginDialog.onLoginFailed();
    }
    
    public void onRegisterSuccess() {
        loginDialog.onRegisterSuccess();
    }
    
    public void onRegisterFailed() {
        loginDialog.onRegisterFailed();
    }
    
    public void onGroupCreated(String groupName) {
        groupsModel.addElement(groupName);
        JOptionPane.showMessageDialog(this,
            "Group created successfully",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void onJoinedGroup(String groupName) {
        JOptionPane.showMessageDialog(this,
            "Joined group: " + groupName,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void onDisconnected() {
        JOptionPane.showMessageDialog(this,
            "Disconnected from server",
            "Connection Lost",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
