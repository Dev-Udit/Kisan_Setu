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
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private double area;   // acres
    private String sellStatus;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;


    @Transient
    private Double ureaRequired;

    @Transient
    private Double dapRequired;

    @Setter
    @Getter
    @Column(nullable = false)
    private boolean fertilizerRequested = false;

    private String deliveryMode;
    private String tokenNumber;
    private String slotDetails; // e.g., "Gate 1 - 10:00 AM"



}
