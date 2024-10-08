package com.ticketmanagement.projection;

import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface TicketAndSeatJoinedProjection {
    String getFirstName();
    String getLastName();
    String getEmail();
    Long getSeatId();
    String getSection();
    Integer getSeatNumber();
    Boolean getIsTaken();
    UUID getTicketId();
    UUID getUserId();
}
