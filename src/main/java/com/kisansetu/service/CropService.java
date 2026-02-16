package com.kisansetu.service;

import com.kisansetu.entity.Crop;
import com.kisansetu.entity.Farmer;
import com.kisansetu.entity.FertilizerRule;
import com.kisansetu.entity.FertilizerRequest;
import com.kisansetu.repository.CropRepository;
import com.kisansetu.repository.FarmerRepository;
import com.kisansetu.repository.FertilizerRequestRepository;
import com.kisansetu.repository.FertilizerRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRepository cropRepository;
    private final FarmerRepository farmerRepository;
    private final FertilizerRequestRepository fertilizerRequestRepository;
    private final FertilizerRuleRepository fertilizerRuleRepository;

    public CropService(CropRepository cropRepository,
                       FarmerRepository farmerRepository,
                       FertilizerRequestRepository fertilizerRequestRepository,
                       FertilizerRuleRepository fertilizerRuleRepository) {
        this.cropRepository = cropRepository;
        this.farmerRepository = farmerRepository;
        this.fertilizerRequestRepository = fertilizerRequestRepository;
        this.fertilizerRuleRepository = fertilizerRuleRepository;
    }

    public Crop addCrop(Long farmerId, Crop crop) {
        crop.setSellStatus("NOT_SOLD");

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        double usedArea = cropRepository.findByFarmerId(farmerId)
                .stream()
                .mapToDouble(Crop::getArea)
                .sum();

        if (usedArea + crop.getArea() > farmer.getLandArea()) {
            throw new RuntimeException("Crop area exceeds total land area of " + farmer.getLandArea() + " acres.");
        }

        crop.setFarmer(farmer);
        return cropRepository.save(crop);
    }

    public List<Crop> getCropsForFarmer(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }

    public List<Crop> getCropsWithFertilizerStatus(Long farmerId) {
        List<Crop> crops = cropRepository.findByFarmerId(farmerId);

        for (Crop crop : crops) {
            // Calculation logic for urea and dap
            FertilizerRule rule = fertilizerRuleRepository.findByCropName(crop.getCropName());

            if (rule != null) {
                crop.setUreaRequired(rule.getUreaPerAcre() * crop.getArea());
                crop.setDapRequired(rule.getDapPerAcre() * crop.getArea());
            }

            Optional<FertilizerRequest> requestOpt =
                    fertilizerRequestRepository.findByCropId(crop.getId());

            crop.setFertilizerRequested(requestOpt.isPresent());
        }

        return crops;
    }



    public List<Crop> getPendingSellRequests() {
        return cropRepository.findBySellStatus("PENDING_APPROVAL");
    }

    public void approveCropSale(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop record not found"));

        crop.setSellStatus("SOLD");
        crop.setTokenNumber("TKN-" + (System.currentTimeMillis() % 10000));


        String mode = crop.getDeliveryMode();

        if ("Mandi Slot".equalsIgnoreCase(mode)) {
            crop.setSlotDetails("Mandi Gate No. 2 | Arrival: 10:00 AM");
        } else if ("Home Pickup".equalsIgnoreCase(mode)) {
            crop.setSlotDetails("Truck Assigned | Pickup: Jan 31st");
        } else {
            crop.setSlotDetails("Contact Mandi Office for Details");
        }

        cropRepository.save(crop);
    }

    public List<Crop> getAllSoldCrops() {
        return cropRepository.findBySellStatus("SOLD");
    }

    public List<Crop> getCropsByFarmer(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }


    public Crop getCropById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id: " + id));
    }
    public void saveCrop(Crop crop) {
        cropRepository.save(crop);
    }
}