package com.kisansetu.service;

import com.kisansetu.entity.Crop;
import com.kisansetu.entity.CropSellRequest;
import com.kisansetu.repository.CropRepository;
import com.kisansetu.repository.CropSellRepository;
import org.springframework.stereotype.Service;

@Service
public class CropSellService {

    private final CropRepository cropRepository;
    private final CropSellRepository sellRepository;

    public CropSellService(CropRepository cropRepository,
                           CropSellRepository sellRepository) {
        this.cropRepository = cropRepository;
        this.sellRepository = sellRepository;
    }

    public CropSellRequest sellCrop(Long cropId, String mode) {


        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        sellRepository.findByCropId(cropId).ifPresent(existing -> {
            if (!"COMPLETED".equals(existing.getStatus())) {
                throw new RuntimeException("Crop already submitted for selling");
            }
        });

        CropSellRequest request = new CropSellRequest();
        request.setCrop(crop);
        request.setMode(mode);
        request.setStatus("PENDING");

        crop.setSellStatus("SELL_REQUESTED");
        cropRepository.save(crop);

        return sellRepository.save(request);
    }
}
