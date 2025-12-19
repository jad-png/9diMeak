package com.PCM._diMeak.repository;

import com.PCM._diMeak.model.Carrier;
import com.PCM._diMeak.model.enums.Specialty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends MongoRepository<Carrier, String> {

    boolean existsByUsername(String username);

    List<Carrier> findBySpecialty(Specialty specialty);

    List<Carrier> findByActiveTrue();

}
