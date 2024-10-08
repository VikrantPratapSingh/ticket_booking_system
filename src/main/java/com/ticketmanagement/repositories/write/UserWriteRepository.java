package com.ticketmanagement.repositories.write;

import com.ticketmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface UserWriteRepository extends JpaRepository<UserEntity, UUID> {
}
