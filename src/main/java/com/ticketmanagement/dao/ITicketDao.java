package com.ticketmanagement.dao;

import com.ticketmanagement.entities.TicketEntity;

public interface ITicketDao {
    TicketEntity save(TicketEntity ticketEntity);
}
