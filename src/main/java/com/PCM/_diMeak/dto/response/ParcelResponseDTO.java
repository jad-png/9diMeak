package com.PCM._diMeak.dto.response;


import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParcelResponseDTO {
    private String id;
    private Specialty type;
    private Double weight;
    private String destinationAddress;
    private ParcelStatus status;
    private Instant createdAt;
    private String carrierId;

    // FRAGILE
    private String handlingInstructions;

    // FRIGO
    private Double minTemperature;
    private Double maxTemperature;
}
