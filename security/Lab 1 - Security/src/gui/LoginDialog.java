package gui;

import service.ChatService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private boolean loginSuccess = false;
    private ChatService chatService;
    
    public LoginDialog(Frame parent, ChatService chatService) {
        super(parent, "Login", true);
        this.chatService = chatService;
        
        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(parent);
        
        // Create components
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
        
        // Add enter key listener
        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        };
        
        usernameField.addKeyListener(enterListener);
        passwordField.addKeyListener(enterListener);
    }
    
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        chatService.login(username, password);
    }
    
    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        chatService.register(username, password);
    }
    
    public void onLoginSuccess(String sessionToken) {
        loginSuccess = true;
        dispose();
    }
    
    public void onLoginFailed() {
        JOptionPane.showMessageDialog(this,
            "Invalid username or password",
            "Login Failed",
            JOptionPane.ERROR_MESSAGE);
    }
    
    public void onRegisterSuccess() {
        JOptionPane.showMessageDialog(this,
            "Registration successful! Please login.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void onRegisterFailed() {
        JOptionPane.showMessageDialog(this,
            "Username already exists",
            "Registration Failed",
            JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean isLoginSuccess() {
        return loginSuccess;
    }
} 