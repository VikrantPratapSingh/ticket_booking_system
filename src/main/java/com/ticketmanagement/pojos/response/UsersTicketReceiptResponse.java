package com.ticketmanagement.pojos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketmanagement.pojos.context.TicketInfo;
import lombok.*;

import java.util.List;
import java.util.UUID;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersTicketReceiptResponse {
    private UUID userId;
    private String from;
    private String to;
    private String message;
    private List<TicketInfo> ticketInfos;
}
