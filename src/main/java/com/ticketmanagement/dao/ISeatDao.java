package com.ticketmanagement.dao;


import com.ticketmanagement.entities.SeatEntity;
import com.ticketmanagement.exceptions.TicketRelatedException;

import java.util.List;
/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface ISeatDao {

    SeatEntity save(SeatEntity seatEntity);

    SeatEntity fetchAvailableSeats();

    List<SeatEntity> findAllById(List<Long> seatId);

    SeatEntity findById(Long seatId) throws TicketRelatedException;

}
