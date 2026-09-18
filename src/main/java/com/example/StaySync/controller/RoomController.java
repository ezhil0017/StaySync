package com.example.StaySync.controller;

import com.example.StaySync.dto.RoomCreateRequest;
import com.example.StaySync.dto.RoomResponse;
import com.example.StaySync.response.ApiResponse;
import com.example.StaySync.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
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
    public ApiResponse<List<RoomResponse>> getAllRooms(){
        List<RoomResponse> responses= roomService.getAllRooms();
        return new ApiResponse<>(
                200,"room fetched successfully",responses,OffsetDateTime.now(),true
        );
}

@GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable Long id){
    RoomResponse room = roomService.getRoomById(id);
    return new ApiResponse<>(
            200,
            "Room fetched successfully",
            room,
            OffsetDateTime.now(),true);
}

@PostMapping
    public ApiResponse<RoomResponse> createRoom(@Valid @RequestBody RoomCreateRequest request){
        RoomResponse roomResponse= roomService.createRoom(request);
        return new ApiResponse<>(
                200,"Room Inserted Successfully",roomResponse,OffsetDateTime.now(),true
        );
}

@PutMapping("/{id}")
    public ApiResponse<RoomResponse> updateRoom(@Valid @RequestBody RoomCreateRequest request,@PathVariable Long id) {
        RoomResponse updatedResponse= roomService.updateRoom(id,request);
        return new ApiResponse<>(
                200,"Room Updated Succesfully",updatedResponse,OffsetDateTime.now(),true
        );
}
@DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id){
         roomService.deleteRoom(id);
}
}
