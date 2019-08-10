package com.dww.insurance.service;

import com.dww.insurance.domain.DriverInfo;
import com.dww.insurance.repository.DriverInfoRepository;

public class DriverInfoService {

    private DriverInfoRepository driverInfoRepository;

    public int insert(DriverInfo driverInfo) {
        return driverInfoRepository.insert(driverInfo);
    }

    public void update(DriverInfo driverInfo) {
        driverInfoRepository.update(driverInfo);
    }

    public void delete(int id) {
        driverInfoRepository.delete(id);
    }

    public void setDriverInfoRepository(DriverInfoRepository driverInfoRepository) {
        this.driverInfoRepository = driverInfoRepository;
    }
}
