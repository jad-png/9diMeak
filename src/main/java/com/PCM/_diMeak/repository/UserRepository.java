package com.PCM._diMeak.repository;

import com.PCM._diMeak.model.User;
import com.PCM._diMeak.model.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
