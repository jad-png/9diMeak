package com.PCM._diMeak.service.interfaces;

import com.PCM._diMeak.dto.request.CarrierRequestDTO;
import com.PCM._diMeak.dto.response.UserResponseDTO;
import com.PCM._diMeak.model.enums.Specialty;

import java.util.List;

public interface CarrierService {
    UserResponseDTO createCarrier(CarrierRequestDTO req);
    List<UserResponseDTO> getCarriersBySpecialty(Specialty specialty);
}
