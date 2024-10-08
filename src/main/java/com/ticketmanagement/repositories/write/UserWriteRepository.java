package com.ticketmanagement.repositories.write;

import com.ticketmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWriteRepository extends JpaRepository<UserEntity, UUID> {
}
