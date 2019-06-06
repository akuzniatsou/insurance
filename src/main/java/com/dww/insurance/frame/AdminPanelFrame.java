package com.dww.insurance.frame;

import com.dww.insurance.domain.*;
import com.dww.insurance.model.SearchResultTableModel;
import com.dww.insurance.model.UserTableModel;
import com.dww.insurance.service.DriverRepository;
import com.dww.insurance.service.SearchRepository;
import com.dww.insurance.service.UserRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelFrame extends JPanel {

    private static final int BASE_LINE = 180;
    private static final int BASE_WIDTH = 240;
    private static final int BASE_HEIGHT = 20;

    private final UserRepository userRepository;

    private JTextField surnameTextField;
    private JComboBox<UserRole> userRoleComboBox;
    private JTextField bodyTextField;
    private DefaultListModel<Credentials> listModel = new DefaultListModel<>();
    private IApplication app;

    private JLabel driverSurname;
    private JLabel driverName;
    private JLabel driverPassId;
    private JLabel driverAddress;
    private JLabel driverPhone;
    private JLabel vehicleModel;
    private JLabel vehicleType;
    private JLabel vehicleNumber;
    private JLabel vehicleBodyId;

    private DamageReport report;
    private JPanel bottomPanel;
    private JLabel wIcon;

    private JTable table;


    public AdminPanelFrame(IApplication app) {
        this.app = app;
        userRepository = new UserRepository();
        initialize();
    }

    public void initialize() {
        removeAll();
        setLayout(null);
        initSearchResult();
        initSearchTab();
        initBottomPanel();
        setVisible(true);
    }

    private void initBottomPanel() {
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBounds(90, 520, 200, 40);
        bottomPanel.setVisible(false);

        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(event -> {
            AdminPanelFrame.this.updateUI();
            app.edit(report);
        });

        bottomPanel.add(editBtn);
        add(bottomPanel);
    }

    private void initSearchResult() {
        JSeparator searchResultSeparator = new JSeparator();
        searchResultSeparator.setBounds(5, 50, 785, 2);
        add(searchResultSeparator);

        List<Credentials> tableData = new ArrayList<>();
        UserTableModel tableModel = new UserTableModel(tableData);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(20, 60, 750, 200);
        add(scrollPaneTable);
    }

    private void initSearchTab() {
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBounds(20, 5, 750, 40);

        searchPanel.add(new JLabel("Login"));

        surnameTextField = new JTextField(10);
        searchPanel.add(surnameTextField);

        searchPanel.add(new JLabel("Role"));

        JPanel type = new JPanel(new BorderLayout());
        userRoleComboBox = new JComboBox<>(UserRole.values());
        userRoleComboBox.setSelectedItem(UserRole.ALL);
        type.add(userRoleComboBox);
        searchPanel.add(type);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(event -> search());
        searchPanel.add(btnSearch);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            surnameTextField.setText("");
            userRoleComboBox.setSelectedItem(UserRole.ALL);
        });
        searchPanel.add(clearButton);

        JButton btnLogout = new JButton("Exit");
        btnLogout.addActionListener(e -> {
            AdminPanelFrame.this.updateUI();
            app.login();
        });
        searchPanel.add(btnLogout);


        add(searchPanel);
    }

    private void search() {
        if (surnameTextField.getText().isEmpty()
                && UserRole.valueOf(userRoleComboBox.getSelectedItem().toString()) == UserRole.ALL) {
            List<Credentials> searchResults = userRepository.findUsers();
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new UserTableModel(searchResults));
        } else {
            Credentials credentials = new Credentials(surnameTextField.getText(), null);
            credentials.setRole(UserRole.valueOf(userRoleComboBox.getSelectedItem().toString()));
            List<Credentials> searchResults = userRepository.find(credentials);
            searchResults.forEach(person -> listModel.addElement(person));
            table.setModel(new UserTableModel(searchResults));
        }
    }

}
