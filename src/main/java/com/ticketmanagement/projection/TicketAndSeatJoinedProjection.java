package com.ticketmanagement.projection;

import java.util.UUID;

public interface TicketAndSeatJoinedProjection {

    // From UserEntity
    String getFirstName();
    String getLastName();
    String getEmail();

    Long getSeatId();
    String getSection();
    Boolean getIsEnabled();
    Integer getSeatNumber();
    Boolean getIsTaken();

    // From TicketEntity
    UUID getTicketId();
    UUID getUserId(); // To correlate with UserEntity
    String getFromLocation();
    String getToLocation();
    Integer getPricePaid();

}
