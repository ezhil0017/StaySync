package com.example.StaySync.controller;

import com.example.StaySync.dto.RoomCreateRequest;
import com.example.StaySync.dto.RoomResponse;
import com.example.StaySync.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        System.out.println("RoomService Created");
        this.roomService=roomService;
    }

@GetMapping
    public List<RoomResponse> getAllRooms(){
        return roomService.getAllRooms();
}

@GetMapping("/{id}")
    public RoomResponse getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
}

@PostMapping
    public RoomResponse createRoom(@Valid @RequestBody RoomCreateRequest request){
        return roomService.createRoom(request);
}

@PutMapping("/{id}")
    public RoomResponse updateRoom(@Valid @RequestBody RoomCreateRequest request,@PathVariable Long id) {
        return roomService.updateRoom(id,request);
}
@DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id){
         roomService.deleteRoom(id);
}
}
