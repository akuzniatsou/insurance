package com.dww.insurance;

import static javax.swing.SwingUtilities.*;

import com.dww.insurance.frame.MainApp;
import com.dww.insurance.frame.TestApp;

public class InsuranceApp {

    public static void main(String[] args) {
        invokeLater(() -> {
            MainApp startFrame = new MainApp("Start Page");
//            TestApp startFrame = new TestApp();
            startFrame.setVisible(true);
        });
    }
}
