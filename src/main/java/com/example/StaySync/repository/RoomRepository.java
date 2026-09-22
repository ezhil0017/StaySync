package com.example.StaySync.repository;

import com.example.StaySync.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long>{

    @Query("""
    SELECT r
    FROM Room r
    JOIN FETCH r.roomType
    WHERE r.roomId = :roomId
""")
    Optional<Room> findRoomWithRoomTypeById(@Param("roomId") Long roomId);
}
