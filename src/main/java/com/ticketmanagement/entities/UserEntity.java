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
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String email;
}
