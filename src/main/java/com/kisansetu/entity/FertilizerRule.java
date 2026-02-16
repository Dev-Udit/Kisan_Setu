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
public class FertilizerRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cropName;

    private double ureaPerAcre;        // recommended (kg/acre)
    private double dapPerAcre;
}
