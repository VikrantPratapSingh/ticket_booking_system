package com.ticketmanagement.dao;


import com.ticketmanagement.entities.SeatEntity;
import com.ticketmanagement.exceptions.TicketInformationException;

import java.util.List;

public interface ISeatDao {

    SeatEntity save(SeatEntity seatEntity);

    SeatEntity fetchAvailableSeats();

    List<SeatEntity> findAllById(List<Long> seatId);

    SeatEntity findById(Long seatId) throws TicketInformationException;

}
