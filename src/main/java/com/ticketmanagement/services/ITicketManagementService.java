package com.ticketmanagement.services;

import com.ticketmanagement.exceptions.SeatInformationException;
import com.ticketmanagement.exceptions.TicketInformationException;
import com.ticketmanagement.exceptions.UserInformationException;
import com.ticketmanagement.pojos.request.BookTicketRequest;
import com.ticketmanagement.pojos.request.UpdateTicketRequest;
import com.ticketmanagement.pojos.response.BookTicketResponse;
import com.ticketmanagement.pojos.response.FetchAllSectionResponse;
import com.ticketmanagement.pojos.response.UpdateTicketResponse;
import com.ticketmanagement.pojos.response.UsersTicketReceiptResponse;

import java.util.UUID;

public interface ITicketManagementService {
    BookTicketResponse bookTicket(BookTicketRequest request) throws TicketInformationException, SeatInformationException, UserInformationException;
    UsersTicketReceiptResponse getUsersTicketReceipt(UUID userId) throws UserInformationException, TicketInformationException;
    UpdateTicketResponse updateTicket(UpdateTicketRequest request) throws TicketInformationException, SeatInformationException;
    Boolean kickUserFromTrain(UUID userId);
    FetchAllSectionResponse viewAllTicketForSection(String section);
}
