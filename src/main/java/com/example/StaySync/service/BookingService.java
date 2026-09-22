package com.example.StaySync.service;

import com.example.StaySync.dto.CreateBookingRequest;
import com.example.StaySync.entity.Booking;
import com.example.StaySync.entity.Guest;
import com.example.StaySync.entity.Room;
import com.example.StaySync.enums.BookingStatus;
import com.example.StaySync.exception.*;
import com.example.StaySync.repository.BookingRepository;
import com.example.StaySync.repository.GuestRepository;
import com.example.StaySync.repository.RoomRepository;
import com.example.StaySync.response.BookingResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final  RoomRepository roomRepository;


    public BookingService(BookingRepository bookingRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    public Guest findGuest(Long guestId){
        return guestRepository.findById(guestId).orElseThrow(()->new GuestNotFoundException("Guest Not Found"));
    }

    public void checkRoomAvailability(Long roomId, OffsetDateTime checkIn, OffsetDateTime checkOut)
    {
        boolean isAlreadyBooked=bookingRepository.existsOverlappingBooking(roomId,checkIn,checkOut, BookingStatus.CANCELLED);
    if(isAlreadyBooked){
        throw new RoomNotAvailableException("Room Not Available for Booking");
    }
    }
    public void validateDates(OffsetDateTime checkIn,OffsetDateTime checkOut){
        if(!checkOut.isAfter(checkIn)){
            throw new InvalidBookingDateException();
        }
    }
    public BigDecimal calculateBookingPrice(Room room,OffsetDateTime checkIn,OffsetDateTime checkOut){
        long numberOfNights= ChronoUnit.DAYS.between(
                checkIn.toLocalDate(),
                checkOut.toLocalDate()
        );
        BigDecimal basePrice=room.getRoomType().getBasePrice();
        return basePrice.multiply(BigDecimal.valueOf(numberOfNights));
    }
    public Room findRoomType(Long roomId){
        return roomRepository.findRoomWithRoomTypeById(roomId).orElseThrow(()->  new RoomNotFoundException(roomId));
    }
public BookingResponse createBooking(CreateBookingRequest request){
        // 1.validate dates
        validateDates(request.getCheckIn(),request.getCheckOut());
    //2. Find guest
    Guest guest=findGuest(request.getGuestId());

    // 3. find room
    Room room=findRoomType(request.getRoomId());

    // 4. checkRoom Availability
    checkRoomAvailability(room.getRoomId(), request.getCheckIn(),request.getCheckOut());

    // 5. calcualte price
    BigDecimal price=calculateBookingPrice(room,request.getCheckIn(),request.getCheckOut());

    // 6. create booking entity
    Booking booking=buildBooking(guest,room,request.getCheckIn(),request.getCheckOut(), request.getNumberOfGuests(), price);

    // 7. save booking
    Booking savedBooking=saveBooking(booking);
    // 8. conver entity to response DTO
    return mapToResponse(savedBooking);
}
public Booking buildBooking(Guest guest,Room room,OffsetDateTime checkIn,OffsetDateTime checkOut,Integer numberOfGuests,BigDecimal price){
    Booking booking=new Booking();
    booking.setGuest(guest);
    booking.setRoom(room);
    booking.setCheckIn(checkIn);
    booking.setCheckOut(checkOut);
    booking.setPrice(price);
    booking.setNumberOfGuests(numberOfGuests);
    booking.setStatus(BookingStatus.PENDING);

    OffsetDateTime now=OffsetDateTime.now();
    booking.setCreatedAt(now);
    booking.setUpdatedAt(now);
    return booking;
}

public Booking saveBooking(Booking booking){

    Booking savedBooking=bookingRepository.save(booking);
        return savedBooking;
}
public BookingResponse mapToResponse(Booking booking){
        BookingResponse response=new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setGuestId(booking.getGuest().getGuestId());
        response.setRoomId(booking.getRoom().getRoomId());
        response.setNumberOfGuests(booking.getNumberOfGuests());
        response.setPrice(booking.getPrice());
        response.setCheckIn(booking.getCheckIn());
        response.setCheckOut(booking.getCheckOut());
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());

        return response;
}
}
