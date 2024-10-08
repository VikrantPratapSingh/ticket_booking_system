package com.ticketmanagement.repositories.read;

import com.ticketmanagement.entities.SeatEntity;
import com.ticketmanagement.projection.TicketAndSeatJoinedProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author vikrantpratapsingh
 * date 8/10/2024
 * time 19:45
 */
public interface SeatReadRepository extends JpaRepository<SeatEntity,Long> {
    SeatEntity findTopByIsOccupiedFalse();
    Optional<SeatEntity> findBySeatNumberAndSectionAndIsOccupiedFalse(Integer seatNumber, String section);

    @Query(value = "SELECT s.seatId as seatId ," +
            " s.seatNumber as seatNumber, " +
            "s.section as section, " +
            "s.isOccupied as isTaken , " +
            "t.isEnabled as IsEnabled" +
            " ,t.id as ticketId ," +
            " u.email as email ," +
            " u.firstName as firstName ," +
            " u.lastName as lastName ," +
            " u.id as userId " +
            " FROM SeatEntity s " +
            " LEFT JOIN TicketEntity t ON t.seatId = s.seatId" +
            " LEFT JOIN UserEntity u on u.id = t.userId" +
            " WHERE s.section = ?1 ")
    List<TicketAndSeatJoinedProjection> findAllTicketAndUserBySection(String sectionName);


}
