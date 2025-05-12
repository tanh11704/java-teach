package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;

public class CircleIcon implements Icon {
    private final Color color;
    private final int size;

    public CircleIcon(Color color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(x, y + (getIconHeight() - size) / 2, size, size);
        g2d.dispose();
    }

    @Override
    public int getIconWidth() {
        return size + 5;
    }

    @Override
    public int getIconHeight() {
        return 16;
    }
    
}
