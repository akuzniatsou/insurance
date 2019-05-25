package com.dww.insurance.frame;

import com.dww.insurance.domain.DamageReport;

public interface IApplication {
    void search();
    void edit();
    void edit(DamageReport report);
}
