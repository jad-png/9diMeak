package com.PCM._diMeak.mapper;

import com.PCM._diMeak.dto.response.ParcelResponseDTO;
import com.PCM._diMeak.model.FragileParcel;
import com.PCM._diMeak.model.Parcel;
import com.PCM._diMeak.model.RefrigeratedParcel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParcelMapper {

    ParcelResponseDTO toResponse(Parcel parcel);

}
