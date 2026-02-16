package com.kisansetu.service;

import com.kisansetu.entity.FertilizerRule;
import com.kisansetu.repository.FertilizerRuleRepository;
import org.springframework.stereotype.Service;

@Service
public class FertilizerRuleService {

    private final FertilizerRuleRepository ruleRepository;

    public FertilizerRuleService(FertilizerRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public FertilizerRule saveRule(FertilizerRule rule) {
        return ruleRepository.save(rule);
    }

    public FertilizerRule getRuleForCrop(String cropName) {
        return ruleRepository.findByCropNameIgnoreCase(cropName)
                .orElseThrow(() ->
                        new RuntimeException("No fertilizer rule found for crop: " + cropName));
    }
}
