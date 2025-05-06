package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import utils.Constants;

public class ContactListCellRenderer extends DefaultListCellRenderer {
    private final boolean isGroupList;
    
    public ContactListCellRenderer(boolean isGroupList) {
        this.isGroupList = isGroupList;
    }
    
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        String name = (String) value;

        // Avatar label
        JLabel avatarLabel = new JLabel(isGroupList ? "👥" : "👤");
        avatarLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        avatarLabel.setHorizontalAlignment(JLabel.CENTER);
        avatarLabel.setVerticalAlignment(JLabel.CENTER);

        // Text content
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SF Pro Text", Font.BOLD, 14));

        JLabel messageLabel = new JLabel("Last message preview...");
        messageLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 12));
        messageLabel.setForeground(Constants.MAC_GREY_TEXT);

        textPanel.add(nameLabel, BorderLayout.NORTH);
        textPanel.add(messageLabel, BorderLayout.SOUTH);

        // Time and status
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);

        JLabel timeLabel = new JLabel("12:30 PM");
        timeLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 11));
        timeLabel.setForeground(Constants.MAC_GREY_TEXT);

        rightPanel.add(timeLabel, BorderLayout.NORTH);

        // Add components to panel
        panel.add(avatarLabel, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        // Set background based on selection
        if (isSelected) {
            panel.setBackground(Constants.MAC_LIGHT_BLUE);
        } else {
            panel.setBackground(Constants.MAC_SIDEBAR);
        }

        panel.setOpaque(true);
        return panel;
    }
}
