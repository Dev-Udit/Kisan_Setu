package com.kisansetu.controller;

import com.kisansetu.entity.Crop;
import com.kisansetu.entity.Farmer;
import com.kisansetu.entity.FertilizerRequest;
import com.kisansetu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired private FarmerService farmerService;
    @Autowired private CropService cropService;
    @Autowired private FertilizerRequestService fertilizerRequestService;

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        Farmer farmer = farmerService.getFarmerByMobile(principal.getName());
        model.addAttribute("farmer", farmer);
        model.addAttribute("crops", cropService.getCropsForFarmer(farmer.getId()));
        model.addAttribute("fertilizerRequests", fertilizerRequestService.getRequestsByFarmer(farmer.getId()));
        return "farmer/dashboard";
    }

    @GetMapping("/add-crop")
    public String addCropPage(Principal principal, Model model) {
        Farmer farmer = farmerService.getFarmerByMobile(principal.getName());
        model.addAttribute("farmerId", farmer.getId());
        return "farmer/add-crop";
    }

    @GetMapping("/sell-crop")
    public String sellCropPage(Principal principal, Model model) {
        Farmer farmer = farmerService.getFarmerByMobile(principal.getName());
        model.addAttribute("crops", cropService.getCropsForFarmer(farmer.getId()));
        return "farmer/sell-crop";
    }

    @GetMapping("/fertilizer")
    public String fertilizerPage(Principal principal, Model model) {
        Farmer farmer = farmerService.getFarmerByMobile(principal.getName());

        List<Crop> crops = cropService.getCropsWithFertilizerStatus(farmer.getId());

        for (Crop crop : crops) {
            crop.setUreaRequired(crop.getArea() * 50);
            crop.setDapRequired(crop.getArea() * 25);
        }

        model.addAttribute("crops", crops);
        return "farmer/request-fertilizer";
    }

    @PostMapping("/request-fertilizer")
    public String submitFertilizer(@RequestParam Long cropId,
                                   @RequestParam Double ureaQty,
                                   @RequestParam Double dapQty,
                                   Principal principal) {

        Crop crop = cropService.getCropById(cropId);


        if (crop.isFertilizerRequested()) {
            return "redirect:/farmer/dashboard";
        }

        Farmer farmer = farmerService.getFarmerByMobile(principal.getName());

        FertilizerRequest req = new FertilizerRequest();
        req.setCrop(crop);
        req.setFarmer(farmer);
        req.setUreaQty(ureaQty);
        req.setDapQty(dapQty);
        req.setStatus("PENDING_APPROVAL");

        fertilizerRequestService.saveRequest(req);


        crop.setFertilizerRequested(true);
        cropService.saveCrop(crop);

        return "redirect:/farmer/dashboard";
    }


    @PostMapping("/submit-sell-request")
    public String submitRequest(@RequestParam("cropId") Long cropId,
                                @RequestParam("deliveryMode") String deliveryMode) {
        Crop crop = cropService.getCropById(cropId);
        crop.setDeliveryMode(deliveryMode);
        crop.setSellStatus("PENDING_APPROVAL");
        cropService.saveCrop(crop);
        return "redirect:/farmer/dashboard";
    }
}