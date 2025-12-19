package com.PCM._diMeak.service.impl;

import com.PCM._diMeak.dto.request.CarrierRequestDTO;
import com.PCM._diMeak.dto.response.UserResponseDTO;
import com.PCM._diMeak.exception.BadRequestException;
import com.PCM._diMeak.mapper.CarrierMapper;
import com.PCM._diMeak.model.Carrier;
import com.PCM._diMeak.model.User;
import com.PCM._diMeak.model.enums.Role;
import com.PCM._diMeak.model.enums.Specialty;
import com.PCM._diMeak.repository.UserRepository;
import com.PCM._diMeak.service.interfaces.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierServiceImpl implements CarrierService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final CarrierMapper mapper;

    public UserResponseDTO createCarrier(CarrierRequestDTO dto) {
        if (repo.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        Carrier carrier = mapper.toEntity(dto);

        carrier.setPassword(encoder.encode(dto.getPassword()));
        carrier.setRole(Role.CARRIER);

        User saved = repo.save(carrier);

        return mapper.toResponse(saved);
    }

    public List<UserResponseDTO> getCarriersBySpecialty(Specialty specialty) {
        return repo.findByRole(Role.CARRIER).stream()
                .filter(u -> u instanceof Carrier c && c.getSpecialty() == specialty)
                .map(mapper::toResponse).toList();
    }
}
