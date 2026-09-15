package com.example.StaySync.entity;

import com.example.StaySync.enums.RoomTypeStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "room_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_room_type_name",
                        columnNames = "name"
                )
        }
)
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(
            name = "base_price",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RoomTypeStatus status = RoomTypeStatus.ACTIVE;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms = new ArrayList<>();

    // Getters and Setters
}