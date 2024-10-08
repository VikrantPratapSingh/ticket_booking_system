package com.ticketmanagement.controllers.admin;

import com.ticketmanagement.exceptions.SeatRelatedException;
import com.ticketmanagement.exceptions.TicketRelatedException;
import com.ticketmanagement.exceptions.UserRelatedException;
import com.ticketmanagement.pojos.request.BookTicketRequest;
import com.ticketmanagement.pojos.request.UpdateTicketRequest;
import com.ticketmanagement.pojos.response.BaseResponse;
import com.ticketmanagement.services.ITicketManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TicketManagementController {
    private ITicketManagementService iTicketManagementService;

    public TicketManagementController(ITicketManagementService iTicketManagementService) {
        this.iTicketManagementService = iTicketManagementService;
    }


    @PostMapping("/book-ticket")
    public ResponseEntity<BaseResponse> createTicket(@RequestBody BookTicketRequest request)
            throws TicketRelatedException, UserRelatedException, SeatRelatedException {
        return ResponseEntity.ok(BaseResponse.builder().data(iTicketManagementService.bookTicket(request))
                .status(HttpStatus.OK)
                .build());
    }


    @PutMapping("/update-ticket")
    public ResponseEntity<BaseResponse> updateTicket(@RequestBody UpdateTicketRequest request)
            throws TicketRelatedException, SeatRelatedException {
        return ResponseEntity.ok(BaseResponse.builder()
                .data(iTicketManagementService.updateTicket(request))
                .status(HttpStatus.OK)
                .build());
    }

    @GetMapping("/fetch/{section}")
    public ResponseEntity<BaseResponse> getAllUserTickets(@PathVariable(value = "section") String section) {
        return ResponseEntity.ok(BaseResponse.builder()
                .data(iTicketManagementService.viewAllTicketForSection(section))
                .status(HttpStatus.OK)
                .build());
    }

    @DeleteMapping("/ticket/{userId}")
    public ResponseEntity<BaseResponse> deleteAllUserTicket(@PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.ok(BaseResponse.builder()
                .data(iTicketManagementService.kickUserFromTrain(userId))
                .status(HttpStatus.OK)
                .message("Deleted Successfully")
                .build());
    }

    @GetMapping("/ticket/{userId}")
    public ResponseEntity<BaseResponse> getAllTicketsOfUser(@PathVariable(value = "userId") UUID userId)
            throws TicketRelatedException, UserRelatedException {
        return ResponseEntity.ok(BaseResponse.builder()
                .data(iTicketManagementService.getUsersTicketReceipt(userId))
                .status(HttpStatus.OK)
                .build());
    }

}
