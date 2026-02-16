package com.kisansetu.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeError(RuntimeException ex, RedirectAttributes redirectAttributes) {
        // Captures the "Crop area exceeds..." message and prevents the app from crashing
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/farmer/dashboard";
    }
}