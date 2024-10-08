package com.ticketmanagement.repositories.read;

import com.ticketmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface UserReadRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findById(UUID userId);
    Optional<UserEntity> findByEmail(String email);
}
