package com.example.StaySync.repository;

import com.example.StaySync.entity.Booking;
import com.example.StaySync.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("""
            select count(b) > 0
            from Booking b
            where b.room.roomId = :roomId
            and b.status <> :cencelledStatus
            and b.checkIn < :checkOut
            and b.checkOut >:checkIn
            """)

    boolean existsOverlappingBooking(
            @Param("roomId") Long roomId,
            @Param("checkIn") OffsetDateTime checkIn,
            @Param("checkOut") OffsetDateTime checkOut,
            @Param("cancelledStatus")BookingStatus cancelledStatus
            );
}
