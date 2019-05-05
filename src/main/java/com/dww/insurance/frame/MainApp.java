package com.dww.insurance.frame;

import javax.swing.*;
import java.awt.*;

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
        cardLayout.show(cardPanel, "Search");

    }

    @Override
    public void search() {
        setTitle("Search");
        cardLayout.show(cardPanel, "Search");
        searchFrame.initialize();
    }


    @Override
    public void edit() {
        setTitle("Edit");
        cardLayout.show(cardPanel, "Edit");
        editFrame.initialize();
    }


    private void initialize() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());

        setTitle(name);
        setSize(800, 600);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);

    }

}
