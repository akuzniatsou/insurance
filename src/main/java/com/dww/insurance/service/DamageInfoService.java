package com.dww.insurance.service;

import com.dww.insurance.domain.DamageInfo;
import com.dww.insurance.repository.DamageInfoRepository;

public class DamageInfoService {

    private DamageInfoRepository damageInfoRepository;

    public void insert(int vehicleId, DamageInfo damageInfo) {
        damageInfoRepository.insert(vehicleId, damageInfo);
    }

    public void update(int vehicleId, DamageInfo damageInfo) {
        damageInfoRepository.update(vehicleId, damageInfo);
    }

    public void delete(int vehicleId) {
        damageInfoRepository.delete(vehicleId);
    }

    public void setDamageInfoRepository(DamageInfoRepository damageInfoRepository) {
        this.damageInfoRepository = damageInfoRepository;
    }
}
