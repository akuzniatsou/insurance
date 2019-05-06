package com.dww.insurance.frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TestApp extends JFrame {
    CardLayout cardLayout;
    JPanel cardPanel;
    String name;
    UserLogin userLogin;
    SearchFrame searchFrame;
    EditFrame editFrame;

    public TestApp() {
        initialize();
        start();
    }

    private void start() {
        setLayout(new FlowLayout());
        JLabel label = new JLabel(){
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.red);
                g.drawOval(0, 0, 50, 50);
            }

        };
//        label.setMinimumSize(new Dimension(100, 100));
        label.setOpaque(true);
        label.setBounds(200, 200, 400,400);
        label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(label);



    }


    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());

        setTitle("test");
        setSize(800, 600);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);

    }

}
