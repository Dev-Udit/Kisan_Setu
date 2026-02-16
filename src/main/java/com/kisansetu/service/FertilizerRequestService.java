package com.kisansetu.service;

import com.kisansetu.entity.*;
import com.kisansetu.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FertilizerRequestService {

    private final CropRepository cropRepository;
    private final FertilizerRuleRepository ruleRepository;
    private final FertilizerRequestRepository requestRepository;

    public FertilizerRequestService(CropRepository cropRepository,
                                    FertilizerRuleRepository ruleRepository,
                                    FertilizerRequestRepository requestRepository) {
        this.cropRepository = cropRepository;
        this.ruleRepository = ruleRepository;
        this.requestRepository = requestRepository;
    }

    // Logic for the automated request (Calculates based on Database Rules)
    public FertilizerRequest requestFertilizer(Long cropId) {
        if (requestRepository.findByCropId(cropId).isPresent()) {
            throw new RuntimeException("Fertilizer already requested for this crop");
        }

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        FertilizerRule rule = ruleRepository
                .findByCropNameIgnoreCase(crop.getCropName())
                .orElseThrow(() -> new RuntimeException("No fertilizer rule found for: " + crop.getCropName()));

        double urea = crop.getArea() * rule.getUreaPerAcre();
        double dap  = crop.getArea() * rule.getDapPerAcre();

        FertilizerRequest request = new FertilizerRequest();
        request.setCrop(crop);
        request.setUreaQty(urea);
        request.setDapQty(dap);
        request.setStatus("PENDING_APPROVAL"); // Standardized Status
        request.setFarmer(crop.getFarmer());

        return requestRepository.save(request);
    }


    public void saveRequest(FertilizerRequest req) {
        requestRepository.save(req);
    }

    @Transactional
    public void approveFertilizerRequest(Long id, String remarks) {
        FertilizerRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("DISPATCHED");
        request.setRemarks(remarks);
        requestRepository.save(request);
    }

    public List<FertilizerRequest> getAllPendingRequests() {
        return requestRepository.findByStatus("PENDING_APPROVAL");
    }

    public List<FertilizerRequest> getAllCompletedRequests() {
        return requestRepository.findByStatus("DISPATCHED");
    }

    public List<FertilizerRequest> getRequestsByFarmer(Long farmerId) {
        return requestRepository.findByFarmerId(farmerId);
    }
}