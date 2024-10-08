package com.ticketmanagement.dao;

import com.ticketmanagement.entities.TicketEntity;
/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface ITicketDao {
    TicketEntity save(TicketEntity ticketEntity);
}
