package com.dww.insurance.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame(String uname) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        setTitle(uname);
        setSize(500, 140);
        setResizable(false);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();


        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



        panel.add(usernameLabel);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(okButton);
        panel.add(cancelButton);




        getContentPane().add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
