package com.ticketmanagement.dao.impl;


import com.ticketmanagement.dao.ITicketDao;
import com.ticketmanagement.entities.TicketEntity;
import com.ticketmanagement.exceptions.TicketInformationException;
import com.ticketmanagement.repositories.read.TicketReadRepository;
import com.ticketmanagement.repositories.write.TicketWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketDao implements ITicketDao {
    private final TicketReadRepository ticketReadRepository;
    private final TicketWriteRepository ticketWriteRepository;

    public TicketDao(TicketReadRepository ticketReadRepository, TicketWriteRepository ticketWriteRepository) {
        this.ticketReadRepository = ticketReadRepository;
        this.ticketWriteRepository = ticketWriteRepository;
    }

    @Override
    public TicketEntity save(TicketEntity ticketEntity) {
        return ticketWriteRepository.save(ticketEntity);
    }

    public List<TicketEntity> findAllTicketsOfUser(UUID userId) {
        return ticketReadRepository.findByUserIdAndIsEnabledTrue(userId);
    }

    public TicketEntity findByTicketId(UUID ticketId) throws TicketInformationException {
        return ticketReadRepository.findByIdAndIsEnabledTrue(ticketId).orElseThrow(() -> new TicketInformationException(String.format("Not found {}",ticketId)));
    }
    public Optional<TicketEntity> disableTicket(UUID ticketId){
        return ticketReadRepository.findByIdAndIsEnabled(ticketId,false);
    }

    public void deleteTickets(List<TicketEntity> ticketEntity){
        ticketWriteRepository.deleteAll(ticketEntity);
    }

}
