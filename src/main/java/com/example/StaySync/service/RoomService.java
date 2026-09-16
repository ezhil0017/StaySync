package com.example.StaySync.service;

import com.example.StaySync.dto.RoomCreateRequest;
import com.example.StaySync.dto.RoomResponse;
import com.example.StaySync.entity.Hotel;
import com.example.StaySync.entity.Room;
import com.example.StaySync.entity.RoomType;
import com.example.StaySync.repository.HotelRepository;
import com.example.StaySync.repository.RoomRepository;
import com.example.StaySync.repository.RoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;
    public RoomService(RoomRepository roomRepository,HotelRepository hotelRepository,RoomTypeRepository roomTypeRepository){
        this.roomRepository=roomRepository;
        this.hotelRepository=hotelRepository;
        this.roomTypeRepository=roomTypeRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }

    public Room createRoom(RoomCreateRequest request) {
        Hotel hotel=hotelRepository.findById(request.getHotelId()).orElseThrow(()->new RuntimeException("Hotel Not Found"));
        RoomType roomType=roomTypeRepository.findById(request.getRoomTypeId()).orElseThrow(()->new RuntimeException("RoomType Not Found"));
        Room room=new Room();
        room.setHotel(hotel);
        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus());
        room.setFloor(request.getFloor());
        room.setRoomType(roomType);

        OffsetDateTime now=OffsetDateTime.now();
        room.setCreatedAt(now);
        room.setUpdatedAt(now);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id,RoomCreateRequest request){
        Room room=roomRepository.findById(id).orElseThrow(()->new RuntimeException("Room Not Found"));
        Hotel hotel=hotelRepository.findById(request.getHotelId()).orElseThrow(()->new RuntimeException("Hotel Not Found"));
        RoomType roomType=roomTypeRepository.findById(request.getRoomTypeId()).orElseThrow(()->new RuntimeException("RoomType Not Found"));

        OffsetDateTime now=OffsetDateTime.now();
        room.setHotel(hotel);
        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus());
        room.setFloor(request.getFloor());
        room.setRoomType(roomType);
        room.setUpdatedAt(now);
        return roomRepository.save(room);

    }
    public void deleteRoom(Long id){
        Room room=roomRepository.findById(id).orElseThrow(()->new RuntimeException("Room Not Found"));
        roomRepository.delete(room);
    }

    private RoomResponse toRoomResponse(Room room) {

        RoomResponse response = new RoomResponse();

        response.setRoomId(room.getRoomId());
        response.setHotelId(room.getHotel().getHotelId());
        response.setRoomTypeId(room.getRoomType().getRoomTypeId());
        response.setRoomNumber(room.getRoomNumber());
        response.setFloor(room.getFloor());
        response.setStatus(room.getStatus());

        return response;
    }
}
