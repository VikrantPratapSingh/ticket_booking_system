package com.ticketmanagement.dao.impl;

import com.ticketmanagement.dao.ISeatDao;
import com.ticketmanagement.entities.SeatEntity;
import com.ticketmanagement.exceptions.TicketInformationException;
import com.ticketmanagement.pojos.response.FetchAllSectionResponse;
import com.ticketmanagement.projection.TicketAndSeatJoinedProjection;
import com.ticketmanagement.repositories.read.SeatReadRepository;
import com.ticketmanagement.repositories.write.SeatWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatDao implements ISeatDao {

    private final SeatReadRepository seatReadRepository;
    private final SeatWriteRepository seatWriteRepository;

    public SeatDao(SeatReadRepository seatReadRepository, SeatWriteRepository seatWriteRepository) {
        this.seatReadRepository = seatReadRepository;
        this.seatWriteRepository = seatWriteRepository;
    }

    @Override
    public SeatEntity fetchAvailableSeats() {
        return seatReadRepository.findTopByIsOccupiedFalse();
    }

    @Override
    public List<SeatEntity> findAllById(List<Long> seatId) {
        return seatReadRepository.findAllById(seatId);
    }

    @Override
    public SeatEntity findById(Long seatId) throws TicketInformationException {
        return seatReadRepository.findById(seatId).orElseThrow(() -> new TicketInformationException(String.format("Seat with Id : % not found", seatId)));
    }

    public void saveAll(List<SeatEntity> seatEntities) {
         seatWriteRepository.saveAll(seatEntities);
    }

    @Override
    public SeatEntity save(SeatEntity seatEntity) {
        return seatWriteRepository.save(seatEntity);
    }

    public List<TicketAndSeatJoinedProjection> fetchAllTicketsOfSection(String sectionName){
        return seatReadRepository.findAllTicketAndUserBySection(sectionName);
    }

    public SeatEntity findBySeatNumberAndSectionName(Integer seatNumber,String sectionName) throws TicketInformationException {
        return seatReadRepository.findBySeatNumberAndSectionAndIsOccupiedFalse(seatNumber,sectionName)
                .orElseThrow(() -> new TicketInformationException(String.format("No seat and section name combination is present")));
    }

}
