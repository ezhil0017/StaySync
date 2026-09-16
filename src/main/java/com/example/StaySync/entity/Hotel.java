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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
}
