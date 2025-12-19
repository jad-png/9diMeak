package com.PCM._diMeak.service.impl;

import com.PCM._diMeak.dto.response.AuthResponseDTO;
import com.PCM._diMeak.dto.LoginDTO;
import com.PCM._diMeak.dto.RegisterDTO;
import com.PCM._diMeak.exception.BadRequestException;
import com.PCM._diMeak.model.User;
import com.PCM._diMeak.model.enums.Role;
import com.PCM._diMeak.repository.UserRepository;
import com.PCM._diMeak.security.JwtService;
import com.PCM._diMeak.service.interfaces.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtSer;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtSer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtSer = jwtSer;
    }

    public AuthResponseDTO register(RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CARRIER);
        user.setActive(true);
        userRepository.save(user);

        String token = jwtSer.generateToken(user);
        return new AuthResponseDTO(token, jwtSer.getExpiration());
    }

    public AuthResponseDTO login(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtSer.generateToken(user);
        return new AuthResponseDTO(token, jwtSer.getExpiration());
    }
}
