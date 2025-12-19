package com.PCM._diMeak.model;

import com.PCM._diMeak.model.enums.CarrierStatus;
import com.PCM._diMeak.model.enums.Role;
import com.PCM._diMeak.model.enums.Specialty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TypeAlias("Carrier")
public class Carrier extends User {

    private Specialty specialty;

    private CarrierStatus status = CarrierStatus.DISPONIBLE;

    public Carrier(String username, String email, String password, Specialty specialty) {
        super(
                null,
                username,
                email,
                password,
                Role.CARRIER,
                true
        );
        this.specialty = specialty;
        this.status = CarrierStatus.DISPONIBLE;
    }
}
