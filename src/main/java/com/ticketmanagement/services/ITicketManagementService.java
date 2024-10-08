package com.ticketmanagement.services;

import com.ticketmanagement.exceptions.SeatRelatedException;
import com.ticketmanagement.exceptions.TicketRelatedException;
import com.ticketmanagement.exceptions.UserRelatedException;
import com.ticketmanagement.pojos.request.BookTicketRequest;
import com.ticketmanagement.pojos.request.UpdateTicketRequest;
import com.ticketmanagement.pojos.response.BookTicketResponse;
import com.ticketmanagement.pojos.response.FetchAllSectionResponse;
import com.ticketmanagement.pojos.response.UpdateTicketResponse;
import com.ticketmanagement.pojos.response.UsersTicketReceiptResponse;

import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface ITicketManagementService {
    BookTicketResponse bookTicket(BookTicketRequest request) throws TicketRelatedException, SeatRelatedException, UserRelatedException;

    UsersTicketReceiptResponse getUsersTicketReceipt(UUID userId) throws UserRelatedException, TicketRelatedException;

    UpdateTicketResponse updateTicket(UpdateTicketRequest request) throws TicketRelatedException, SeatRelatedException;

    Boolean kickUserFromTrain(UUID userId);

    FetchAllSectionResponse viewAllTicketForSection(String section);
}
