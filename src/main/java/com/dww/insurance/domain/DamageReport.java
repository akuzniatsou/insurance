package com.dww.insurance.domain;

public class DamageReport {

    private DriverInfo driverInfo;
    private VehicleInfo vehicleInfo;
    private DamageInfo damageInfo;

    public DamageReport() {
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
