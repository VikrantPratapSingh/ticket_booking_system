package com.ticketmanagement.repositories.read;

import com.ticketmanagement.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface TicketReadRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findByUserIdAndIsEnabledTrue(UUID userId);
    Optional<TicketEntity> findByIdAndIsEnabled(UUID trackId, Boolean isEnabled);
    Optional<TicketEntity> findByIdAndIsEnabledTrue(UUID ticketId);

}
