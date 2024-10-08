package com.ticketmanagement.dao.impl;


import com.ticketmanagement.dao.IUserDao;
import com.ticketmanagement.entities.UserEntity;
import com.ticketmanagement.exceptions.TicketInformationException;
import com.ticketmanagement.repositories.read.UserReadRepository;
import com.ticketmanagement.repositories.write.UserWriteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDao implements IUserDao {
    private final UserReadRepository userReadRepository;
    private final UserWriteRepository userWriteRepository;

    public UserDao(UserReadRepository userReadRepository, UserWriteRepository userWriteRepository) {
        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
    }

    public UserEntity findByUserId(UUID userId) throws TicketInformationException {
        return userReadRepository.findById(userId)
                .orElseThrow(() -> new TicketInformationException
                        (String.format("user with Id : % not found", userId)));
    }

    public Optional<UserEntity> findByEmailId(String emailId) {
        return userReadRepository.findByEmail(emailId);
    }
    public UserEntity save(UserEntity userEntity){
        return userWriteRepository.save(userEntity);
    }
}
