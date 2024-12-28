package com.carinfo.carinfoo.service;

import com.carinfo.carinfoo.dto.BookingResponse;
import com.carinfo.carinfoo.dto.FetchBookingResponse;
import com.carinfo.carinfoo.model.FlightBooking;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface FlightBookingService {

    BookingResponse bookFlight( FlightBooking booking);

    FetchBookingResponse fetchBooking();
}
