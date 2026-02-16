package com.kisansetu.controller;

import com.kisansetu.entity.FertilizerRule;
import com.kisansetu.service.FertilizerRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fertilizer-rule")
public class FertilizerRuleController {

    private final FertilizerRuleService ruleService;

    public FertilizerRuleController(FertilizerRuleService ruleService) {
        this.ruleService = ruleService;
    }

    // Admin adds/updates rules
    @PostMapping("/add")
    public FertilizerRule addRule(@RequestBody FertilizerRule rule) {
        return ruleService.saveRule(rule);
    }
}
