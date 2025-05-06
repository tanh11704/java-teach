package swing;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

public class TextIcon implements Icon{
    private String text;
    private Component component;

    public TextIcon(String text, Component component) {
        this.text = text;
        this.component = component;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        Font font = new Font("Dialog", Font.PLAIN, 16);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(text, x, y + fm.getAscent());
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 20;
    }

    @Override
    public int getIconHeight() {
        return 20;
    }
}
