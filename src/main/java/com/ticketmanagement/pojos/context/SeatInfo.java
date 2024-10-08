package com.ticketmanagement.pojos.context;

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
public class SeatInfo {
    private Long seatId;
    private String section;
    private UUID ticketId;
    private String from;
    private String to;
    private Integer seatNumber;
    private UsersInfo userInfo;
    private Boolean isOccupied = false;
}