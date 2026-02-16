package com.kisansetu.repository;

import com.kisansetu.entity.FertilizerRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FertilizerRuleRepository extends JpaRepository<FertilizerRule, Long> {

    Optional<FertilizerRule> findByCropNameIgnoreCase(String cropName);

    FertilizerRule findByCropName(String cropName);
}
