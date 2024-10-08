package com.ticketmanagement.services.impl;

import com.ticketmanagement.entities.SeatEntity;
import com.ticketmanagement.entities.TicketEntity;
import com.ticketmanagement.entities.UserEntity;
import com.ticketmanagement.exceptions.SeatInformationException;
import com.ticketmanagement.exceptions.TicketInformationException;
import com.ticketmanagement.exceptions.UserInformationException;
import com.ticketmanagement.pojos.context.SeatInfo;
import com.ticketmanagement.pojos.context.UsersInfo;
import com.ticketmanagement.pojos.request.BookTicketRequest;
import com.ticketmanagement.pojos.context.TicketInfo;
import com.ticketmanagement.pojos.request.UpdateTicketRequest;
import com.ticketmanagement.pojos.response.BookTicketResponse;
import com.ticketmanagement.dao.impl.SeatDao;
import com.ticketmanagement.dao.impl.TicketDao;
import com.ticketmanagement.dao.impl.UserDao;
import com.ticketmanagement.pojos.response.FetchAllSectionResponse;
import com.ticketmanagement.pojos.response.UpdateTicketResponse;
import com.ticketmanagement.pojos.response.UsersTicketReceiptResponse;
import com.ticketmanagement.projection.TicketAndSeatJoinedProjection;
import com.ticketmanagement.services.ITicketManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketManagementService implements ITicketManagementService {
    private final UserDao userDao;
    private final TicketDao ticketDao;
    private final SeatDao seatDao;

    public TicketManagementService(UserDao userDao, TicketDao ticketDao, SeatDao seatDao) {
        this.userDao = userDao;
        this.ticketDao = ticketDao;
        this.seatDao = seatDao;
    }

    @Override
    public BookTicketResponse bookTicket(BookTicketRequest request) throws TicketInformationException, SeatInformationException, UserInformationException {
        //check if seat is available
        SeatEntity seatEntity = seatDao.fetchAvailableSeats();
        if (seatEntity == null) {
            throw new SeatInformationException();
        }
        // check if user is already present or create new user
        UserEntity userEntity = createUserDetails(request);

        // create users Ticket
        TicketEntity ticketEntity = createUsersTicket(request, seatEntity, userEntity);
        //mark seat as taken
        seatEntity.setIsOccupied(true);
        seatDao.save(seatEntity);
        return BookTicketResponse.builder()
                .userId(userEntity.getId())
                .seatNumber(seatEntity.getSeatNumber())
                .section(seatEntity.getSection())
                .ticketId(ticketEntity.getId())
                .from(ticketEntity.getFrom())
                .to(ticketEntity.getTo())
                .pricePaid(ticketEntity.getPricePaid())
                .build();
    }

    @Override
    public UpdateTicketResponse updateTicket(UpdateTicketRequest request) throws TicketInformationException, SeatInformationException {
        // Fetch ticket details
        TicketEntity ticketEntity = ticketDao.findByTicketId(request.getTicketId());

        // Fetch the seat allotted to the ticket
        SeatEntity allottedSeat = seatDao.findById(ticketEntity.getSeatId());

        // If isEnabled is false, disable the ticket and free up the allotted seat
        if (Boolean.FALSE.equals(request.getIsEnabled())) {
            allottedSeat.setIsOccupied(false);
            seatDao.save(allottedSeat);
            ticketDao.deleteTickets(List.of(ticketEntity));
        }

        allottedSeat.setIsOccupied(false);
        seatDao.save(allottedSeat);

        // Find the new seat and assign it to the ticket
        SeatEntity newSeat = seatDao.findBySeatNumberAndSectionName(request.getNewSeatNumber(), request.getNewSection());
        // Assign the new seat to the ticket and mark it as taken
        newSeat.setIsOccupied(true);
        ticketEntity.setSeatId(newSeat.getSeatId());
        // Save changes to the seat and ticket
        SeatEntity seatEntity=seatDao.save(newSeat);
        TicketEntity ticket=ticketDao.save(ticketEntity);
        return UpdateTicketResponse.builder()
                .isEnabled(ticket.getIsEnabled())
                .newSeatNumber(seatEntity.getSeatNumber())
                .newSection(seatEntity.getSection())
                .ticketId(ticket.getId())
                .userId(ticket.getUserId())
                .build();
    }

    @Override
    public UsersTicketReceiptResponse getUsersTicketReceipt(UUID userId) throws UserInformationException, TicketInformationException {
        UserEntity userEntity = userDao.findByUserId(userId);
        if (userEntity != null) {
            // fetch all tickets of user
            List<TicketEntity> tickets = ticketDao.findAllTicketsOfUser(userId);
            //for each ticket create a map of seat id and its entity
            Map<Long, SeatEntity> seatIdMap = seatDao.findAllById(
                            tickets.stream()
                                    .map(TicketEntity::getSeatId)
                                    .toList())
                    .stream()
                    .collect(Collectors.toMap(SeatEntity::getSeatId, Function.identity()));

            List<TicketInfo> ticketInfo = tickets.stream()
                    .map(ticket -> {
                        SeatEntity seat = seatIdMap.get(ticket.getSeatId());
                        return TicketInfo.builder()
                                .ticketId(ticket.getId())
                                .section(seat.getSection())
                                .seatNumber(seat.getSeatNumber())
                                .paidAmount(ticket.getPricePaid())
                                .build();
                    }).toList();

            return UsersTicketReceiptResponse.builder()
                    .userId(userId)
                    .ticketInfos(ticketInfo)
                    .build();
        }
        return UsersTicketReceiptResponse
                .builder()
                .message("The user is not present")
                .build();
    }

    @Override
    public FetchAllSectionResponse viewAllTicketForSection(String section) {
        // Fetch all ticket and seat data for the specified section
        List<TicketAndSeatJoinedProjection> allBySection = seatDao.fetchAllTicketsOfSection(section);

        // Create a list to hold seat information
        List<SeatInfo> seatInfoList = new ArrayList<>();

        for (TicketAndSeatJoinedProjection s : allBySection) {
            // Create a SeatInfo object for each seat
            SeatInfo seatInfo = SeatInfo.builder()
                    .isOccupied(s.getIsTaken())
                    .seatId(s.getSeatId())
                    .seatNumber(s.getSeatNumber())
                    .section(s.getSection())
                    .build();

            // If the seat is occupied, add user info and ticket ID
            if (Boolean.TRUE.equals(s.getIsTaken())) {
                UsersInfo usersInfo = UsersInfo.builder()
                        .userId(s.getUserId())
                        .firstName(s.getFirstName())
                        .lastName(s.getLastName())
                        .emailId(s.getEmail())
                        .build();

                seatInfo.setUserInfo(usersInfo);
                seatInfo.setTicketId(s.getTicketId());
            }
            // Add the constructed seatInfo to the list
            seatInfoList.add(seatInfo);
        }
        return FetchAllSectionResponse.builder()
                .seats(seatInfoList)
                .build();
    }

    @Override
    public Boolean kickUserFromTrain(UUID userId) {
        //fetch all tickets
        List<TicketEntity> tickets = ticketDao.findAllTicketsOfUser(userId);
        //extract all seat id's
        List<Long> seatId = tickets.stream()
                .map(TicketEntity::getSeatId).toList();
        //fetch all seats using seatId list
        List<SeatEntity> seats = seatDao.findAllById(seatId);
        // set all seats as free to be occupied
        seats.forEach(s -> s.setIsOccupied(false));
        seatDao.saveAll(seats);
        //delete all tickets
        ticketDao.deleteTickets(tickets);
        return true;
    }

    private TicketEntity createUsersTicket(BookTicketRequest request, SeatEntity seatEntity, UserEntity userEntity) {
        return ticketDao.save(TicketEntity.builder()
                .seatId(seatEntity.getSeatId())
                .id(UUID.randomUUID())
                .userId(userEntity.getId())
                .pricePaid(request.getTicketPrice())
                .from(request.getFrom())
                .to(request.getTo())
                .isEnabled(true)
                .build());

    }

    private UserEntity createUserDetails(BookTicketRequest request) throws TicketInformationException {
        UserEntity userEntity;
        if (request.getUserId() != null) {
            userEntity = userDao.findByUserId(request.getUserId());
        } else {
            if (userDao.findByEmailId(request.getUserInfo().getEmailId()).isPresent()) {
                throw new TicketInformationException(String.format("User with email %s already exist", request.getUserInfo().getEmailId()));
            }
            // if user is not present ,create a new user
            userEntity = createUserEntity(request);
        }
        return userEntity;
    }

    private UserEntity createUserEntity(BookTicketRequest request) {
        UserEntity userEntity;
        userEntity = userDao.save(UserEntity.builder()
                .id(UUID.randomUUID())
                .lastName(request.getUserInfo().getLastName())
                .firstName(request.getUserInfo().getFirstName())
                .email(request.getUserInfo().getEmailId())
                .build());
        return userEntity;
    }
}
