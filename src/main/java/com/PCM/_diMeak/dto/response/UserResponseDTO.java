package com.PCM._diMeak.dto.response;

import com.PCM._diMeak.model.enums.CarrierStatus;
import com.PCM._diMeak.model.enums.Role;
import com.PCM._diMeak.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private Role role;
    private boolean active;

    // if user is admin these attribute would be null
    private Specialty specialty;
    private CarrierStatus status;
}
