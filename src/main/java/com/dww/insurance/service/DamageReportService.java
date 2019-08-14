package com.dww.insurance.service;

import com.dww.insurance.dto.DamageReport;

public class DamageReportService {

    private DamageInfoService damageInfoService;
    private DriverInfoService driverInfoService;
    private VehicleInfoService vehicleInfoService;

    public void insert(DamageReport damageReport) {
        int driverId = driverInfoService.insert(damageReport.getDriverInfo());
        int vehicleId = vehicleInfoService.insert(driverId, damageReport.getVehicleInfo());
        damageInfoService.insert(vehicleId, damageReport.getDamageInfo());
    }

    public void update(DamageReport damageReport) {
        driverInfoService.update(damageReport.getDriverInfo());
        vehicleInfoService.update(damageReport.getVehicleInfo());
        damageInfoService.update(damageReport.getVehicleInfo().getId(), damageReport.getDamageInfo());
    }

    public void delete(DamageReport damageReport) {
        damageInfoService.delete(damageReport.getDamageInfo().getId());
        vehicleInfoService.delete(damageReport.getVehicleInfo().getId());
        driverInfoService.delete(damageReport.getDriverInfo().getId());
    }

    public void setDamageInfoService(DamageInfoService damageInfoService) {
        this.damageInfoService = damageInfoService;
    }

    public void setDriverInfoService(DriverInfoService driverInfoService) {
        this.driverInfoService = driverInfoService;
    }

    public void setVehicleInfoService(VehicleInfoService vehicleInfoService) {
        this.vehicleInfoService = vehicleInfoService;
    }
}
