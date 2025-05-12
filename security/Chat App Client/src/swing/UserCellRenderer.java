package swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class UserCellRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID = 1L;
        
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            // Add online indicator
            label.setIcon(new CircleIcon(Color.GREEN, 8));
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            return label;
        }
}
