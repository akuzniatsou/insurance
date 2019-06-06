package com.dww.insurance.frame;

import com.dww.insurance.domain.DamageReport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainApp extends JFrame implements IApplication {
    CardLayout cardLayout;
    JPanel cardPanel;
    String name;
    UserLogin userLogin;
    SearchFrame searchFrame;
    EditFrame editFrame;


    public MainApp(String uname) {
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

        cardPanel.add(userLogin, "Login");
        cardPanel.add(searchFrame, "Search");
        cardPanel.add(editFrame, "Edit");

        add(cardPanel);
        setTitle("Login");
        cardLayout.show(cardPanel, "Login");

    }

    @Override
    public void search() {
        setTitle("Search");
        cardLayout.show(cardPanel, "Search");
        searchFrame.initialize();
    }


    @Override
    public void edit() {
        setTitle("Create");
        cardLayout.show(cardPanel, "Edit");
        editFrame.initialize(new DamageReport());
    }

    @Override
    public void edit(DamageReport report) {
        setTitle("Edit");
        cardLayout.show(cardPanel, "Edit");
        editFrame.initialize(report);
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
                this, "Are you sure you want to quit?", "Please confirm", JOptionPane.YES_NO_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
