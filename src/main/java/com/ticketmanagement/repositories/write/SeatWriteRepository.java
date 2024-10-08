package com.ticketmanagement.repositories.write;

import com.ticketmanagement.entities.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatWriteRepository extends JpaRepository<SeatEntity,Long> {
}
