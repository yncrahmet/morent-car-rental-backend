package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.BookingRequest;
import com.archisacademy.morent.dtos.responses.BookingDetailsResponse;
import com.archisacademy.morent.dtos.responses.BookingResponse;

import java.util.UUID;


public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    String deleteBooking(Long id);
    BookingDetailsResponse getBookingDetails(Long id);
}
