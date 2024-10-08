package com.ticketmanagement.repositories.read;

import com.ticketmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findById(UUID userId);
    Optional<UserEntity> findByEmail(String email);
}
