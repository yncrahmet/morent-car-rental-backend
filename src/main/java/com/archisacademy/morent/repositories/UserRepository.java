package com.archisacademy.morent.repositories;

import com.archisacademy.morent.dtos.requests.UserDTO;
import com.archisacademy.morent.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUserId(UUID userId);


}
