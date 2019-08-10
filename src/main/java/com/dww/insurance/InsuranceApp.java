package com.dww.insurance;

import static javax.swing.SwingUtilities.invokeLater;

import com.dww.insurance.frame.MainFrame;
import com.dww.insurance.repository.DamageInfoRepository;
import com.dww.insurance.repository.DriverInfoRepository;
import com.dww.insurance.repository.UserRepository;
import com.dww.insurance.repository.VehicleInfoRepository;
import com.dww.insurance.service.DamageInfoService;
import com.dww.insurance.service.DamageReportService;
import com.dww.insurance.service.DriverInfoService;
import com.dww.insurance.service.ServiceLocator;
import com.dww.insurance.service.UserService;
import com.dww.insurance.service.VehicleInfoService;

public class InsuranceApp {

    public static void main(String[] args) {
        initServices();
        invokeLater(() -> {
            MainFrame startFrame = new MainFrame("Start Page");

            startFrame.setVisible(true);
        });
    }

    private static void initServices() {
        ServiceLocator locator = new ServiceLocator();

        DriverInfoService driverInfoService = new DriverInfoService();
        driverInfoService.setDriverInfoRepository(new DriverInfoRepository());
        locator.registerService(DriverInfoService.class, driverInfoService);

        UserService userService = new UserService();
        userService.setUserRepository(new UserRepository());
        locator.registerService(UserService.class, userService);

        VehicleInfoService vehicleInfoService = new VehicleInfoService();
        vehicleInfoService.setVehicleInfoRepository(new VehicleInfoRepository());
        locator.registerService(VehicleInfoService.class, vehicleInfoService);

        DamageInfoService damageInfoService = new DamageInfoService();
        damageInfoService.setDamageInfoRepository(new DamageInfoRepository());
        locator.registerService(DamageInfoService.class, damageInfoService);

        DamageReportService damageReportService = new DamageReportService();
        damageReportService.setDamageInfoService(damageInfoService);
        damageReportService.setVehicleInfoService(vehicleInfoService);
        damageReportService.setDriverInfoService(driverInfoService);
        locator.registerService(DamageReportService.class, damageReportService);

        ServiceLocator.setLocator(locator);
    }
}
