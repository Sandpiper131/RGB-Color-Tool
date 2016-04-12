import javax.swing.*;
import java.awt.*;

/**
 * Created by Sandpiper on 4/9/2016.
 */
public class GraphicsPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Main.color);
        g.fillRect(getWidth() - 1, getHeight() - 2, 25, 25);
    }

    protected void refreshColor() {
        repaint();
    }
}
