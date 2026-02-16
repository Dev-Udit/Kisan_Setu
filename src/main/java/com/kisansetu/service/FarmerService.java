package com.kisansetu.service;

import com.kisansetu.entity.Farmer;
import com.kisansetu.repository.FarmerRepository;
import org.springframework.stereotype.Service;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public Farmer getFarmerByMobile(String mobile) {
        return farmerRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("Farmer not found with mobile: " + mobile));
    }

    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
    }
}
