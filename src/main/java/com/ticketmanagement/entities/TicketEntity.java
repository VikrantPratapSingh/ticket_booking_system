package com.ticketmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 7/10/2024
 * time 19:00
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "from_location", nullable = false)
    private String from;

    @Column(name = "to_location", nullable = false)
    private String to;

    @Column(name = "paid_price", nullable = false)
    private Integer pricePaid;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

}
