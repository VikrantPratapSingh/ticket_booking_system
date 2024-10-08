package com.ticketmanagement.pojos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketmanagement.pojos.context.UsersInfo;
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
public class BookTicketRequest {
    private UUID userId;
    private String from;
    private String to;
    private Integer ticketPrice;
    private UsersInfo userInfo;
}