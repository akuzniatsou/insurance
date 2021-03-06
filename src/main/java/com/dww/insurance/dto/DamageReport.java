package com.dww.insurance.dto;

import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.domain.VehicleInfo;

public class DamageReport {

    private DriverInfo driverInfo;
    private VehicleInfo vehicleInfo;
    private DamageInfo damageInfo;

    public DamageReport() {
        driverInfo = new DriverInfo();
        vehicleInfo = new VehicleInfo();
        damageInfo = new DamageInfo(new boolean[13]);
    }

    public DamageReport(DriverInfo driverInfo, VehicleInfo vehicleInfo, DamageInfo damageInfo) {
        this.driverInfo = driverInfo;
        this.vehicleInfo = vehicleInfo;
        this.damageInfo = damageInfo;
    }

    public DriverInfo getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfo driverInfo) {
        this.driverInfo = driverInfo;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public DamageInfo getDamageInfo() {
        return damageInfo;
    }

    public void setDamageInfo(DamageInfo damageInfo) {
        this.damageInfo = damageInfo;
    }
}
