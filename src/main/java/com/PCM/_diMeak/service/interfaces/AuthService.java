package com.PCM._diMeak.service.interfaces;

import com.PCM._diMeak.dto.response.AuthResponseDTO;
import com.PCM._diMeak.dto.LoginDTO;
import com.PCM._diMeak.dto.RegisterDTO;

public interface AuthService {
    public AuthResponseDTO register(RegisterDTO dto);
    public AuthResponseDTO login(LoginDTO dto);
}
