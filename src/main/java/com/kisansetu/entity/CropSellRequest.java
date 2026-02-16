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
public class CropSellRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Crop crop;

    private String mode;     // HOME_PICKUP or MANDI_SLOT
    private String status;   // PENDING, APPROVED, COMPLETED

    private String approvedBy;
}
