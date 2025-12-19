package com.PCM._diMeak.model;

import com.PCM._diMeak.model.enums.Specialty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TypeAlias("Fragile")
public class FragileParcel extends Parcel {

    private String handlingInstructions;

    public FragileParcel(
            Double weight,
            String destinationAddress,
            String handlingInstructions
    ) {
        super(null, Specialty.FRAGILE, weight, destinationAddress);
        this.handlingInstructions = handlingInstructions;
    }
}
