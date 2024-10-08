package com.ticketmanagement.dao;

import com.ticketmanagement.entities.UserEntity;
import com.ticketmanagement.exceptions.TicketInformationException;

import java.util.Optional;
import java.util.UUID;

public interface IUserDao {
    UserEntity findByUserId(UUID userId) throws TicketInformationException;

    Optional<UserEntity> findByEmailId(String email);
}
