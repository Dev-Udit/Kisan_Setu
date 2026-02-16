package com.kisansetu.repository;

import com.kisansetu.entity.FertilizerRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FertilizerRequestRepository
        extends JpaRepository<FertilizerRequest, Long> {

    Optional<FertilizerRequest> findByCropId(Long cropId);

    boolean existsByCropFarmerIdAndStatus(Long farmerId, String pending);

    boolean existsByCropId(Long id);

    // Finds all requests filtered by their current status (e.g., PENDING)
    List<FertilizerRequest> findByStatus(String status);

    List<FertilizerRequest> findByFarmerId(Long farmerId);

    @Modifying
    @Transactional
    @Query("UPDATE FertilizerRequest f SET f.status = :status, f.remarks = :remarks WHERE f.id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") String status, @Param("remarks") String remarks);
}
