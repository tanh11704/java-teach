import config.ClientConfig;
import javax.swing.*;
import view.LoginView;

public class ClientApplication {    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Không thể thiết lập giao diện hệ thống: " + e.getMessage());
        }
        
        ClientConfig config = new ClientConfig();
        
        SwingUtilities.invokeLater(() -> {
            new LoginView(config).setVisible(true);
        });
    }
}
