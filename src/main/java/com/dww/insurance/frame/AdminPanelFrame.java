package com.dww.insurance.frame;

import com.dww.insurance.InsuranceApp;
import com.dww.insurance.domain.User;
import com.dww.insurance.domain.UserRole;
import com.dww.insurance.model.UserTableModel;
import com.dww.insurance.service.ServiceLocator;
import com.dww.insurance.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AdminPanelFrame extends JPanel {

    private UserService userService = ServiceLocator.getService(UserService.class);

    private JTextField searchLoginTextField = new JTextField(22);
    private JTextField newLoginTextField = new JTextField(22);
    private JPasswordField newPassTextField = new JPasswordField(22);
    private JButton deleteButton;
    private JComboBox<UserRole> newRole;

    private JComboBox<UserRole> userRoleComboBox;
    private DefaultListModel<User> listModel = new DefaultListModel<>();
    private IMainFrame app;

    private JPanel bottomPanel;
    private User credentials = new User();
    private JTable table;

    AdminPanelFrame(IMainFrame app) {
        this.app = app;
        initialize();
    }

    void initialize() {
        removeAll();
        setLayout(null);
        initSearchResult();
        initSearchTab();
        initBottomPanel();
        setVisible(true);
        addUserTab();
    }

    private void initSearchResult() {
        JSeparator searchResultSeparator = new JSeparator();
        searchResultSeparator.setBounds(5, 50, 785, 2);
        add(searchResultSeparator);

        List<User> tableData = new ArrayList<>();
        UserTableModel tableModel = new UserTableModel(tableData);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.requestFocus();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                credentials = ((UserTableModel) table.getModel()).getValue(table.convertRowIndexToModel(table.getSelectedRow()));
                deleteButton.setVisible(true);
            }
        });
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(20, 60, 750, 200);
        add(scrollPaneTable);
    }

    private void initSearchTab() {
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBounds(20, 5, 750, 40);

        searchPanel.add(new JLabel(InsuranceApp.getMessage("label_login")));

        searchLoginTextField = new JTextField(10);
        searchPanel.add(searchLoginTextField);

        searchPanel.add(new JLabel(InsuranceApp.getMessage("label_role")));

        JPanel type = new JPanel(new BorderLayout());
        userRoleComboBox = new JComboBox<>(UserRole.values());
        userRoleComboBox.setSelectedItem(UserRole.ALL);
        type.add(userRoleComboBox);
        searchPanel.add(type);

        JButton btnSearch = new JButton(InsuranceApp.getMessage("label_search"));
        btnSearch.addActionListener(event -> search());
        searchPanel.add(btnSearch);

        JButton clearButton = new JButton(InsuranceApp.getMessage("label_clear"));
        clearButton.addActionListener(e -> {
            searchLoginTextField.setText("");
            userRoleComboBox.setSelectedItem(UserRole.ALL);
            table.getSelectionModel().clearSelection();
            deleteButton.setVisible(false);
        });
        searchPanel.add(clearButton);

        JButton btnLogout = new JButton(InsuranceApp.getMessage("label_logout"));
        btnLogout.addActionListener(e -> {
            AdminPanelFrame.this.updateUI();
            app.login();
        });
        searchPanel.add(btnLogout);


        add(searchPanel);
    }

    private void initBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBounds(90, 520, 200, 40);
        bottomPanel.setVisible(true);

        JButton addBtn = new JButton(InsuranceApp.getMessage("label_save"));
        addBtn.addActionListener(event -> {
            AdminPanelFrame.this.updateUI();
            save();
        });
        deleteButton = new JButton(InsuranceApp.getMessage("label_delete"));
        deleteButton.setBackground(new Color(250, 128, 114));
        deleteButton.addActionListener(event -> delete());
        deleteButton.setVisible(false);

        bottomPanel.add(addBtn);
        bottomPanel.add(deleteButton);
        add(bottomPanel);
    }

    private void addUserTab() {
        JPanel addUserTab = new JPanel();
        addUserTab.setBounds(20, 280, 350, 100);
        addUserTab.setLayout(new BoxLayout(addUserTab, BoxLayout.Y_AXIS));

        JPanel title = new JPanel(new BorderLayout());
        title.add(new JLabel(InsuranceApp.getMessage("label_add_user")), BorderLayout.WEST);

        JPanel role = new JPanel(new BorderLayout());
        newRole = new JComboBox<>();
        newRole.addItem(UserRole.ADMIN);
        newRole.addItem(UserRole.USER);
        newRole.addItem(UserRole.UNAUTHORIZED);
        newRole.setSelectedItem(UserRole.UNAUTHORIZED);
        role.add(newRole);

        addUserTab.add(title);
        addUserTab.add(new JSeparator());
        addUserTab.add(Box.createVerticalStrut(5));
        addUserTab.add("Login", createComponent(InsuranceApp.getMessage("label_login"), newLoginTextField));
        addUserTab.add("Pass", createComponent(InsuranceApp.getMessage("label_pass"), newPassTextField));
        addUserTab.add("Role", createComponent(InsuranceApp.getMessage("label_role"), role));

        addUserTab.setVisible(true);
        add(addUserTab);
    }

    private void save() {
        if (empty(newLoginTextField, newLoginTextField) && newRole.getSelectedItem() == UserRole.UNAUTHORIZED) {
            JOptionPane.showMessageDialog(this, InsuranceApp.getMessage("notification_validation_failed"),
                InsuranceApp.getMessage("notification_label_validation_failed"), JOptionPane.ERROR_MESSAGE);
        } else {
            User credentials = new User();
            credentials.setRole((UserRole) newRole.getSelectedItem());
            credentials.setLogin(newLoginTextField.getText());
            credentials.setPassword(String.valueOf(newPassTextField.getPassword()));
            userService.insertUser(credentials);
            clearNewUser();
        }
    }

    private void clearNewUser() {
        newLoginTextField.setText("");
        newPassTextField.setText("");
        newRole.setSelectedItem(UserRole.UNAUTHORIZED);
    }

    private void delete() {
        if (credentials != null && credentials.getLogin() != null) {
            int confirmDialog = JOptionPane.showConfirmDialog(
                    this, InsuranceApp.getMessage("notification_delete"),
                InsuranceApp.getMessage("notification_label_error"), JOptionPane.YES_NO_OPTION);
            if (confirmDialog == JOptionPane.YES_OPTION) {
                userService.deleteUser(credentials.getLogin());
                bottomPanel.setVisible(false);
                initialize();
                search();
            }
        }
    }

    private void search() {
        deleteButton.setVisible(false);
        List<User> searchResults;
        if (searchLoginTextField.getText().isEmpty()
                && UserRole.valueOf(userRoleComboBox.getSelectedItem().toString()) == UserRole.ALL) {
            searchResults = userService.findUsers();
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new UserTableModel(searchResults));
        } else {
            User credentials = new User(searchLoginTextField.getText(), null);
            credentials.setRole(UserRole.valueOf(userRoleComboBox.getSelectedItem().toString()));
            searchResults = userService.find(credentials);
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new UserTableModel(searchResults));
        }
        if (!searchResults.isEmpty()) {
            bottomPanel.setVisible(true);
        }
    }

    private JPanel createComponent(String label, Component textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(textField, BorderLayout.EAST);
        return panel;
    }

    private boolean empty(JTextField... textFields) {
        return Arrays.stream(textFields).map(JTextField::getText).anyMatch(String::isEmpty);
    }

}
