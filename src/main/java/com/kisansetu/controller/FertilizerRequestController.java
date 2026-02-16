package com.kisansetu.controller;

import com.kisansetu.entity.FertilizerRequest;
import com.kisansetu.service.FertilizerRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fertilizer")
public class FertilizerRequestController {

    private final FertilizerRequestService requestService;

    public FertilizerRequestController(FertilizerRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/request/{cropId}")
    public FertilizerRequest request(@PathVariable Long cropId) {
        return requestService.requestFertilizer(cropId);
    }



}
