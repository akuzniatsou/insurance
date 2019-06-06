package com.dww.insurance.frame;

import com.dww.insurance.domain.Credentials;
import com.dww.insurance.service.UserRepository;

import javax.swing.*;
import java.awt.*;


public class UserLogin extends JPanel {

    private UserRepository userRepository;
    private IApplication app;

    public UserLogin(IApplication app) {
        this.app = app;
        userRepository = new UserRepository();
        initialize();
    }

    public void initialize() {
        removeAll();
        setLayout(new GridBagLayout());

        JPanel parent = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> validateAction(textField, passwordField));
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(e -> exitAction());
        panel.add(usernameLabel);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(okButton);
        panel.add(cancelButton);

        parent.add(panel);
        add(parent, gbc);
    }

    private void validateAction(JTextField textField, JPasswordField passwordField) {
        Credentials credentials = userRepository.authorize(new Credentials(textField.getText(), String.valueOf(passwordField.getPassword())));
        switch (credentials.getRole()) {
            case USER:
                app.search();
                break;
            case ADMIN:
                app.adminPanel();
                break;
            case UNAUTHORIZED:
            default:
                JOptionPane.showMessageDialog(this, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exitAction() {
        int confirmDialog = JOptionPane.showConfirmDialog(
            this, "Are you sure you want to quit?", "Please confirm", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
