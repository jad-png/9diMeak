package com.PCM._diMeak.utils.factory;

import com.PCM._diMeak.dto.request.ParcelRequestDTO;
import com.PCM._diMeak.model.FragileParcel;
import com.PCM._diMeak.model.Parcel;
import com.PCM._diMeak.model.RefrigeratedParcel;
import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ParcelFactory {
    public Parcel create(ParcelRequestDTO request) {
        return switch (request.getType()) {
            case FRAGILE -> new FragileParcel(
                    request.getWeight(),
                    request.getDestinationAddress(),
                    request.getHandlingInstructions()
            );
            case FRIGO -> new RefrigeratedParcel(
                    request.getWeight(),
                    request.getDestinationAddress(),
                    request.getMinTemperature(),
                    request.getMaxTemperature()
            );
            case STANDARD -> new Parcel(
                    null,
                    Specialty.STANDARD,
                    request.getWeight(),
                    request.getDestinationAddress()
            );
        };
    }
}
