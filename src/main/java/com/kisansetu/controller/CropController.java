package com.kisansetu.controller;

import com.kisansetu.entity.Crop;
import com.kisansetu.service.CropService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crop")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping("/add/{farmerId}")
    public String addCrop(@PathVariable Long farmerId, @ModelAttribute Crop crop) {
        cropService.addCrop(farmerId, crop);
        return "redirect:/farmer/dashboard";
    }
}