package com.kisansetu.repository;

import com.kisansetu.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByFarmerId(Long farmerId);


    List<Crop> findBySellStatus(String sellRequested);
}
