package com.kisansetu.controller;

import com.kisansetu.entity.Farmer;
import com.kisansetu.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UIController {

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "index"; // Your landing page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Farmer login
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin/login"; // Admin-specific login
    }

    @GetMapping("/register")
    public String register() {
        return "farmer/register";
    }

    @PostMapping("/farmer/signup")
    public String processRegistration(@ModelAttribute Farmer farmer , RedirectAttributes redirectAttributes) {
        // 1. Encrypt the password securely before saving it to the database
        String encodedPassword = passwordEncoder.encode(farmer.getPassword());
        farmer.setPassword(encodedPassword);
        farmerService.saveFarmer(farmer);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login below.");
        return "redirect:/login";
    }
}