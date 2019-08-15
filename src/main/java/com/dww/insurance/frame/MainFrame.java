package com.dww.insurance.frame;

import com.dww.insurance.InsuranceApp;
import com.dww.insurance.dto.DamageReport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements IMainFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String name;
    private UserLogin userLogin;
    private SearchFrame searchFrame;
    private EditFrame editFrame;
    private AdminPanelFrame adminPanelFrame;

    public MainFrame(String uname) {
        this.name = uname;
        initialize();
        start();
    }

    private void start() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        userLogin = new UserLogin(this);
        searchFrame = new SearchFrame(this);
        editFrame = new EditFrame(this);
        adminPanelFrame = new AdminPanelFrame(this);

        cardPanel.add(userLogin, "Login");
        cardPanel.add(searchFrame, "Search");
        cardPanel.add(editFrame, "Edit");
        cardPanel.add(adminPanelFrame, "Admin");

        login();
    }

    @Override
    public void search() {
        setTitle(InsuranceApp.getMessage("label_search"));
        cardLayout.show(cardPanel, "Search");
        searchFrame.initialize();
    }

    @Override
    public void edit() {
        setTitle(InsuranceApp.getMessage("label_create"));
        cardLayout.show(cardPanel, "Edit");
        editFrame.initialize(new DamageReport());
    }

    @Override
    public void edit(DamageReport report) {
        setTitle(InsuranceApp.getMessage("label_edit"));
        cardLayout.show(cardPanel, "Edit");
        editFrame.initialize(report);
    }

    @Override
    public void adminPanel() {
        setTitle(InsuranceApp.getMessage("label_admin_page"));
        cardLayout.show(cardPanel, "Admin");
        adminPanelFrame.initialize();
    }

    @Override
    public void login() {
        add(cardPanel);
        setTitle(InsuranceApp.getMessage("label_login"));
        cardLayout.show(cardPanel, "Login");
        userLogin.initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitAction();
            }
        });

        setTitle(name);
        setSize(800, 600);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);

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
