package com.kisansetu.controller;

import com.kisansetu.entity.Crop;
import com.kisansetu.entity.Farmer;
import com.kisansetu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private CropService cropService;
    @Autowired private FertilizerRequestService fertilizerService;
    @Autowired private FarmerService farmerService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {


        model.addAttribute("pendingSales", cropService.getPendingSellRequests());
        model.addAttribute("pendingFertilizer", fertilizerService.getAllPendingRequests());

        model.addAttribute("soldHistory", cropService.getAllSoldCrops());
        model.addAttribute("fertilizerHistory", fertilizerService.getAllCompletedRequests());

        return "admin/dashboard";
    }

    @PostMapping("/approve-sale/{id}")
    public String approveSale(@PathVariable Long id) {

        cropService.approveCropSale(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/approve-fertilizer/{id}")
    public String approveFertilizer(@PathVariable Long id,
                                    @RequestParam(value="remarks", defaultValue="Approved") String remarks) {

        fertilizerService.approveFertilizerRequest(id, remarks);
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/farmer-profile/{id}")
    public String viewFarmerProfile(@PathVariable Long id, Model model) {
        // Fetches the data requested by your UI
        model.addAttribute("farmer", farmerService.getFarmerById(id));
        model.addAttribute("crops", cropService.getCropsByFarmer(id));
        model.addAttribute("fertilizers", fertilizerService.getRequestsByFarmer(id));
        return "admin/farmer-profile";
    }
}