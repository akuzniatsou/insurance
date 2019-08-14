package com.dww.insurance.frame;

import com.dww.insurance.dto.DamageReport;

public interface IMainFrame {
    void search();
    void edit();
    void edit(DamageReport report);
    void adminPanel();
    void login();
}
