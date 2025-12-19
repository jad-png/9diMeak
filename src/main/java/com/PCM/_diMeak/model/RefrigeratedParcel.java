package com.PCM._diMeak.model;

import com.PCM._diMeak.model.enums.Specialty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TypeAlias("Frigo")
public class RefrigeratedParcel extends Parcel {

    private Double minTemperature;
    private Double maxTemperature;

    public RefrigeratedParcel(
            Double weight,
            String destinationAddress,
            Double minTemperature,
            Double maxTemperature
    ) {
        super(null, Specialty.FRIGO, weight, destinationAddress);
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}
