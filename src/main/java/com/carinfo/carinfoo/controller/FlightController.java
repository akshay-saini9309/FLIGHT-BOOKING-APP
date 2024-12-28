package com.carinfo.carinfoo.controller;

import com.carinfo.carinfoo.dto.BookingResponse;
import com.carinfo.carinfoo.dto.FetchBookingResponse;
import com.carinfo.carinfoo.model.FlightBooking;
import com.carinfo.carinfoo.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class FlightController {

    @Autowired
    private FlightBookingService service;

    @PostMapping("/booking")
    public ResponseEntity<BookingResponse> saveFlights(@Valid @RequestBody FlightBooking booking) {

        BookingResponse response =  service.bookFlight(booking);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @GetMapping("/booking")
    public ResponseEntity<FetchBookingResponse> fetchFlights() {
        FetchBookingResponse response  = service.fetchBooking();
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}