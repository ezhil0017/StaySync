package com.example.StaySync.repository;

import com.example.StaySync.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {}
