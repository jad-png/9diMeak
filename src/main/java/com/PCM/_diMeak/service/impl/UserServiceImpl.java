package com.PCM._diMeak.service.impl;

import com.PCM._diMeak.dto.request.CarrierRequestDTO;
import com.PCM._diMeak.dto.response.UserResponseDTO;
import com.PCM._diMeak.exception.ResourceNotFoundException;
import com.PCM._diMeak.mapper.CarrierMapper;
import com.PCM._diMeak.model.Carrier;
import com.PCM._diMeak.model.User;
import com.PCM._diMeak.model.enums.Specialty;
import com.PCM._diMeak.repository.UserRepository;
import com.PCM._diMeak.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final CarrierMapper mapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public UserResponseDTO toggleUserStatus(String userId, boolean active) {
        User user = repo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        user.setActive(active);

        User saved = repo.save(user);

        return mapper.toResponse(saved);
    }

    @Override
    public void deleteUser(String userId) {
        if (!repo.existsById(userId)) {
            throw new ResourceNotFoundException("Utilisateur non trouvé");
        }
        repo.deleteById(userId);
    }
}
