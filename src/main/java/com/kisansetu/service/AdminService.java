package com.kisansetu.service;

import com.kisansetu.entity.CropSellRequest;
import com.kisansetu.repository.CropSellRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final CropSellRepository sellRepository;

    public AdminService(CropSellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    public CropSellRequest approveSell(Long sellId, String adminName) {

        CropSellRequest request = sellRepository.findById(sellId)
                .orElseThrow(() -> new RuntimeException("Sell request not found"));

        request.setStatus("APPROVED");
        request.setApprovedBy(adminName);

        return sellRepository.save(request);
    }
}
