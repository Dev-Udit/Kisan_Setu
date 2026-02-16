package com.kisansetu.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = ANY)
public class FertilizerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "crop_id", unique = true, nullable = false)
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    private double ureaQty;
    private double dapQty;

    private String status;    // PENDING, APPROVED, DELIVERED

    private String deliveryMode;
    private String approvedBy;

    private String remarks; // For admin messages like "Collect from Gate 1"

}
