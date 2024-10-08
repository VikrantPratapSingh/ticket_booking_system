package com.ticketmanagement.repositories.write;

import com.ticketmanagement.entities.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */

public interface SeatWriteRepository extends JpaRepository<SeatEntity,Long> {
}
