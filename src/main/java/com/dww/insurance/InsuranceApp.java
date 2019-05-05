package com.dww.insurance;

import static javax.swing.SwingUtilities.*;

import com.dww.insurance.frame.MainApp;

public class InsuranceApp {

    public static void main(String[] args) {
        invokeLater(() -> {
            MainApp startFrame = new MainApp("Start Page");
            startFrame.setVisible(true);
        });
    }
}
