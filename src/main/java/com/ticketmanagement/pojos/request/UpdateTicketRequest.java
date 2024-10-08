package com.ticketmanagement.pojos.request;

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
public class UpdateTicketRequest {
    private UUID ticketId;
    private UUID userId;
    private String newSection;
    private Integer newSeatNumber;
    private Boolean isEnabled;
}