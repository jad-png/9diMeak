package com.PCM._diMeak.mapper;

import com.PCM._diMeak.dto.request.CarrierRequestDTO;
import com.PCM._diMeak.dto.response.UserResponseDTO;
import com.PCM._diMeak.model.Carrier;
import com.PCM._diMeak.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarrierMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", constant = "true")
    Carrier toEntity(CarrierRequestDTO request);

    UserResponseDTO toResponse(Carrier carrier);

    UserResponseDTO toResponse(User user);
}
