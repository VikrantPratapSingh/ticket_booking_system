package com.ticketmanagement.dao;

import com.ticketmanagement.entities.UserEntity;
import com.ticketmanagement.exceptions.TicketRelatedException;

import java.util.Optional;
import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface IUserDao {
    UserEntity findByUserId(UUID userId) throws TicketRelatedException;

    Optional<UserEntity> findByEmailId(String email);
}
