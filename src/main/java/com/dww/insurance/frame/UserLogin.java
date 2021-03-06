package com.dww.insurance.frame;

import com.dww.insurance.InsuranceApp;
import com.dww.insurance.domain.User;
import com.dww.insurance.service.ServiceLocator;
import com.dww.insurance.service.UserService;

import javax.swing.*;
import java.awt.*;


class UserLogin extends JPanel {

    private UserService userService = ServiceLocator.getService(UserService.class);
    private IMainFrame app;

    UserLogin(IMainFrame app) {
        this.app = app;
        initialize();
    }

    void initialize() {
        removeAll();
        setLayout(new GridBagLayout());

        JPanel parent = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        JLabel usernameLabel = new JLabel(InsuranceApp.getMessage("label_username"));
        JLabel passwordLabel = new JLabel(InsuranceApp.getMessage("label_password"));
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton okButton = new JButton(InsuranceApp.getMessage("label_ok").toUpperCase());
        okButton.addActionListener(e -> validateAction(textField, passwordField));
        JButton cancelButton = new JButton(InsuranceApp.getMessage("label_cancel").toUpperCase());
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
        User credentials = userService.authorize(new User(textField.getText(), String.valueOf(passwordField.getPassword())));
        switch (credentials.getRole()) {
            case USER:
                app.search();
                break;
            case ADMIN:
                app.adminPanel();
                break;
            case UNAUTHORIZED:
            default:
                JOptionPane.showMessageDialog(this, InsuranceApp.getMessage("notification_login_failed"),
                    InsuranceApp.getMessage("notification_label_error"), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exitAction() {
        int confirmDialog = JOptionPane.showConfirmDialog(
            this, InsuranceApp.getMessage("notification_quit"),
            InsuranceApp.getMessage("notification_label_confirm"), JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
