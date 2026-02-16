package com.kisansetu.repository;

import com.kisansetu.entity.CropSellRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropSellRepository
        extends JpaRepository<CropSellRequest, Long> {

    Optional<CropSellRequest> findByCropId(Long cropId);
}
