package com.ticketmanagement.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketResponse {
    private UUID userId;
    private String from;
    private String to;
    private Integer pricePaid;
    private UUID ticketId;
    private String section;
    private Integer seatNumber;
}
