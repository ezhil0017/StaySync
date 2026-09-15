package com.example.StaySync.entity;


import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels", uniqueConstraints = {
        @UniqueConstraint(
                name = "uq_hotel_name",
                columnNames = "name"
        )
})
public class Hotel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "hotel_id")
private Long hotelId;

@Column(nullable = false,length = 150)
    private String name;
@Column(length = 500)
    private String address;
@Column(length = 20)
    private String phone;
@Column(length = 255)
    private String email;
@Column(name = "created_at",nullable = false)
    private OffsetDateTime createdAt;
@Column(name = "updated_at",nullable = false)
    private OffsetDateTime updatedAt;
@OneToMany(mappedBy = "hotel")
    private List<Room> rooms=new ArrayList<>();
@OneToMany(mappedBy = "hotel")
    private List<Staff> staff=new ArrayList<>();
}
