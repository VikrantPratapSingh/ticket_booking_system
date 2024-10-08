package com.ticketmanagement.entities;

import jakarta.persistence.*;
import lombok.*;


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
@Table(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long seatId;

    @Column(name = "seat_section", nullable = false)
    private String section;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "is_occupied", nullable = false)
    private Boolean isOccupied = false;

}
