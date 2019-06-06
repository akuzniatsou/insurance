package com.dww.insurance.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SelectionPoint implements Icon {

    private Color color;

    public SelectionPoint(boolean selected) {
        if (selected) {
            color = Color.red;
        } else {
            color = Color.green;
        }
    }

    public void switchColor() {
        if (color == Color.red) {
            color = Color.green;
        } else {
            color = Color.red;
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, 25, 25);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.draw(circle);
    }

    @Override
    public int getIconWidth() {
        return 25;
    }

    @Override
    public int getIconHeight() {
        return 25;
    }

    public boolean selected() {
        return color == Color.red;
    }
}
