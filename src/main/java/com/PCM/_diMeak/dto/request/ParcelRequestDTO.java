package com.PCM._diMeak.dto.request;

import com.PCM._diMeak.model.enums.Specialty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelRequestDTO {

    @NotNull(message = "Le type du colis est obligatoire")
    private Specialty type;

    @NotNull(message = "Le poids est obligatoire")
    @Min(value = 1, message = "Le poids doit être supérieur à 0")
    private Double weight;

    @NotBlank(message = "L'adresse de destination est obligatoire")
    private String destinationAddress;

    // FRAGILE uniquement
    private String handlingInstructions;

    // FRIGO uniquement
    private Double minTemperature;
    private Double maxTemperature;
}

