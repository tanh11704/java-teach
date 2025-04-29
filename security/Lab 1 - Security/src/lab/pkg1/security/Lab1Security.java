package lab.pkg1.security;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Lab1Security {
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private HashMap<String, String> userDatabase;
    private Color primaryColor = new Color(65, 105, 225); // Royal Blue
    private Color textColor = new Color(50, 50, 50);
    private Color bgColor = new Color(240, 240, 240);
    private Font mainFont = new Font("SF Pro Display", Font.PLAIN, 14);
    private Font titleFont = new Font("SF Pro Display", Font.BOLD, 24);

    public Lab1Security() {
        // Thiết lập LookAndFeel của macOS
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.appearance", "system");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Khởi tạo database giả lập
        userDatabase = new HashMap<>();
        
        // Thiết lập giao diện
        setupUI();
    }
    
    private void setupUI() {
        mainFrame = new JFrame("Hệ thống đăng nhập");
        mainFrame.setSize(400, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        
        // Thiết lập card layout để chuyển đổi giữa các màn hình
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Tạo các panel
        JPanel loginPanel = createLoginPanel();
        JPanel registerPanel = createRegisterPanel();
        JPanel welcomePanel = createWelcomePanel();
        
        // Thêm các panel vào cardLayout
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registerPanel, "register");
        cardPanel.add(welcomePanel, "welcome");
        
        // Hiển thị màn hình đăng nhập đầu tiên
        cardLayout.show(cardPanel, "login");
        
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }
    
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(bgColor);
        
        // Logo và tiêu đề
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(bgColor);
        
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/icon/lock.png")));
        if (logoLabel.getIcon() == null) {
            logoLabel = new JLabel("🔐"); // Emoji thay thế nếu không tìm thấy logo
            logoLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 48));
            logoLabel.setHorizontalAlignment(JLabel.CENTER);
        }
        
        JLabel titleLabel = new JLabel("Đăng nhập", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        
        headerPanel.add(logoLabel, BorderLayout.CENTER);
        headerPanel.add(titleLabel, BorderLayout.SOUTH);
        
        // Form đăng nhập
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(bgColor);
        formPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JTextField usernameField = createStyledTextField("Tên đăng nhập");
        JPasswordField passwordField = createStyledPasswordField("Mật khẩu");
        
        JButton loginButton = createStyledButton("Đăng nhập");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (authenticate(username, password)) {
                cardLayout.show(cardPanel, "welcome");
            } else {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Tên đăng nhập hoặc mật khẩu không chính xác!", 
                    "Lỗi đăng nhập", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Thêm các thành phần vào form
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        
        // Footer với link đăng ký
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(bgColor);
        
        JLabel registerLabel = new JLabel("Chưa có tài khoản? ");
        registerLabel.setFont(mainFont);
        registerLabel.setForeground(textColor);
        
        JLabel registerLink = new JLabel("Đăng ký ngay");
        registerLink.setFont(new Font(mainFont.getName(), Font.BOLD, mainFont.getSize()));
        registerLink.setForeground(primaryColor);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "register");
                // Reset các trường nhập liệu
                usernameField.setText("");
                passwordField.setText("");
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                registerLink.setText("<html><u>Đăng ký ngay</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                registerLink.setText("Đăng ký ngay");
            }
        });
        
        footerPanel.add(registerLabel);
        footerPanel.add(registerLink);
        
        // Thêm các panel vào panel chính
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(footerPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(bgColor);
        
        // Tiêu đề
        JLabel titleLabel = new JLabel("Tạo tài khoản mới", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        
        // Form đăng ký
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(bgColor);
        formPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JTextField fullNameField = createStyledTextField("Họ và tên");
        JTextField emailField = createStyledTextField("Email");
        JTextField usernameField = createStyledTextField("Tên đăng nhập");
        JPasswordField passwordField = createStyledPasswordField("Mật khẩu");
        JPasswordField confirmPasswordField = createStyledPasswordField("Xác nhận mật khẩu");
        
        JButton registerButton = createStyledButton("Đăng ký");
        registerButton.addActionListener(e -> {
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            // Kiểm tra thông tin đăng ký
            if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Vui lòng điền đầy đủ thông tin!", 
                    "Lỗi đăng ký", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Mật khẩu xác nhận không khớp!", 
                    "Lỗi đăng ký", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Tên đăng nhập đã tồn tại!", 
                    "Lỗi đăng ký", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Đăng ký thành công
            userDatabase.put(username, password);
            JOptionPane.showMessageDialog(mainFrame, 
                "Đăng ký thành công! Giờ bạn có thể đăng nhập.", 
                "Đăng ký thành công", 
                JOptionPane.INFORMATION_MESSAGE);
                
            // Xóa form và chuyển về màn hình đăng nhập
            fullNameField.setText("");
            emailField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            cardLayout.show(cardPanel, "login");
        });
        
        // Thêm các thành phần vào form
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(fullNameField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(confirmPasswordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(registerButton);
        
        // Footer với link quay lại đăng nhập
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(bgColor);
        
        JLabel loginLabel = new JLabel("Đã có tài khoản? ");
        loginLabel.setFont(mainFont);
        loginLabel.setForeground(textColor);
        
        JLabel loginLink = new JLabel("Đăng nhập");
        loginLink.setFont(new Font(mainFont.getName(), Font.BOLD, mainFont.getSize()));
        loginLink.setForeground(primaryColor);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "login");
                // Reset các trường nhập liệu
                fullNameField.setText("");
                emailField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                loginLink.setText("<html><u>Đăng nhập</u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                loginLink.setText("Đăng nhập");
            }
        });
        
        footerPanel.add(loginLabel);
        footerPanel.add(loginLink);
        
        // Thêm các panel vào panel chính
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(footerPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel("Chào mừng bạn đã đăng nhập thành công!", JLabel.CENTER);
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(textColor);
        
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>Cảm ơn bạn đã sử dụng hệ thống của chúng tôi.<br>Chúc bạn có trải nghiệm tuyệt vời!</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(mainFont);
        descriptionLabel.setForeground(textColor);
        
        JButton logoutButton = createStyledButton("Đăng xuất");
        logoutButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "login");
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(logoutButton);
        
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setFont(mainFont);
        textField.setForeground(textColor);
        textField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(8, 10, 8, 10)
        ));
        
        // Thiết lập placeholder
        TextPrompt tp = new TextPrompt(placeholder, textField);
        tp.setForeground(new Color(150, 150, 150));
        tp.setFont(mainFont);
        tp.changeAlpha(0.7f);
        tp.changeStyle(Font.ITALIC);
        
        // Thiết lập kích thước tối đa
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        return textField;
    }
    
    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(mainFont);
        passwordField.setForeground(textColor);
        passwordField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(8, 10, 8, 10)
        ));
        
        // Thiết lập placeholder
        TextPrompt tp = new TextPrompt(placeholder, passwordField);
        tp.setForeground(new Color(150, 150, 150));
        tp.setFont(mainFont);
        tp.changeAlpha(0.7f);
        tp.changeStyle(Font.ITALIC);
        
        // Thiết lập kích thước tối đa
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        return passwordField;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font(mainFont.getName(), Font.BOLD, mainFont.getSize()));
        button.setForeground(Color.WHITE);
        button.setBackground(primaryColor);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        // Hiệu ứng hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(primaryColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
            }
        });
        
        return button;
    }
    
    private boolean authenticate(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return userDatabase.get(username).equals(password);
        }
        return false;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Lab1Security();
        });
    }
    
    // Class hỗ trợ tạo placeholder cho JTextField
    class TextPrompt extends JLabel implements FocusListener, DocumentListener {
        private JTextComponent component;
        private Document document;

        public TextPrompt(String text, JTextComponent component) {
            this.component = component;
            this.document = component.getDocument();
            
            setText(text);
            setFont(component.getFont());
            setForeground(component.getForeground());
            setBorder(new EmptyBorder(component.getInsets()));
            setHorizontalAlignment(JLabel.LEADING);
            
            component.addFocusListener(this);
            document.addDocumentListener(this);
            
            component.setLayout(new BorderLayout());
            component.add(this);
            checkForPrompt();
        }

        public void changeAlpha(float alpha) {
            setForeground(new Color(getForeground().getRed(),
                                    getForeground().getGreen(),
                                    getForeground().getBlue(),
                                    (int)(alpha * 255)));
        }

        public void changeStyle(int style) {
            setFont(getFont().deriveFont(style));
        }

        private void checkForPrompt() {
            if (document.getLength() > 0) {
                setVisible(false);
            } else {
                setVisible(true);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            checkForPrompt();
        }

        @Override
        public void focusLost(FocusEvent e) {
            checkForPrompt();
        }

        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            checkForPrompt();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            checkForPrompt();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    }
}