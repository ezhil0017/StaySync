package com.example.StaySync.controller;

import com.example.StaySync.dto.RoomCreateRequest;
import com.example.StaySync.entity.Room;
import com.example.StaySync.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        System.out.println("RoomService Created");
        this.roomService=roomService;
    }

@GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
}

@GetMapping("/{id}")
    public Optional<Room> getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
}

@PostMapping
    public Room createRoom(@RequestBody RoomCreateRequest request){
        return roomService.createRoom(request);
}

@PutMapping("/{id}")
    public Room updateRoom(@RequestBody RoomCreateRequest request,@PathVariable Long id) {
        return roomService.updateRoom(id,request);
}
}
