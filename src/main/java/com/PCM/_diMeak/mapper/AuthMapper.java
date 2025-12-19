package com.PCM._diMeak.mapper;

import com.PCM._diMeak.dto.RegisterDTO;
import com.PCM._diMeak.model.User;
import com.PCM._diMeak.model.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {


    @Mapping(target = "username", expression = "java(resolveUsername(dto))")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toUser(RegisterDTO dto);

    default String resolveUsername(RegisterDTO dto) {
        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            return dto.getUsername();
        }
        return dto.getEmail().split("@")[0];
    }
}