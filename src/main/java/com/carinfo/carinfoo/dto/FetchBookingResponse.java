package com.carinfo.carinfoo.dto;

import com.carinfo.carinfoo.model.FlightBooking;

import java.util.List;

public class FetchBookingResponse {

    List<FlightBooking> booking;
    private String status;

    public List<FlightBooking> getBooking() {
        return booking;
    }

    public void setBooking(List<FlightBooking> booking) {
        this.booking = booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
