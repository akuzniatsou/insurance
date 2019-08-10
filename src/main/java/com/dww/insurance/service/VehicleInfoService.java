package com.dww.insurance.service;

import com.dww.insurance.domain.VehicleInfo;
import com.dww.insurance.repository.VehicleInfoRepository;

public class VehicleInfoService {

    private VehicleInfoRepository vehicleInfoRepository;

    public int insert(int ownerId, VehicleInfo vehicleInfo) {
        return vehicleInfoRepository.insert(ownerId, vehicleInfo);
    }

    public void update(VehicleInfo vehicleInfo) {
        vehicleInfoRepository.update(vehicleInfo);
    }

    public void delete(int vehicleId) {
        vehicleInfoRepository.delete(vehicleId);
    }

    public void setVehicleInfoRepository(VehicleInfoRepository vehicleInfoRepository) {
        this.vehicleInfoRepository = vehicleInfoRepository;
    }
}
