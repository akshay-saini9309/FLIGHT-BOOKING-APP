package com.carinfo.carinfoo.serviceImpl;

import com.carinfo.carinfoo.dto.BookingResponse;
import com.carinfo.carinfoo.dto.FetchBookingResponse;
import com.carinfo.carinfoo.model.FlightBooking;
import com.carinfo.carinfoo.repo.FlightBookingRepository;
import com.carinfo.carinfoo.service.FlightBookingService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {

    @Autowired
    private FlightBookingRepository repository;

    private static final String CSV_FILE = "flights_delhi_mumbai.csv";

    @Override
    public BookingResponse bookFlight(FlightBooking booking) {
        BookingResponse response = new BookingResponse();
        try {
            // Save all records to the database
            FlightBooking savedBooking = repository.save(booking);
            System.out.println("SavedBooking " + savedBooking );
            // Check if the booking meets the criteria for saving to CSV
            if ("Delhi".equalsIgnoreCase(booking.getSource()) &&
                    "Mumbai".equalsIgnoreCase(booking.getDestination()) &&
                    booking.getBookingDate().isAfter(LocalDate.now().minusDays(1)) &&
                    booking.getBookingDate().isBefore(LocalDate.now().plusDays(8))) {

                boolean fileExists = new File(CSV_FILE).exists();

                try (FileWriter out = new FileWriter(CSV_FILE, true);
                     CSVPrinter printer = new CSVPrinter(out, fileExists ? CSVFormat.DEFAULT : CSVFormat.DEFAULT.withHeader("email", "source", "destination", "bookingDate", "bookingTime"))) {

                    printer.printRecord(booking.getEmail(), booking.getSource(), booking.getDestination(), booking.getBookingDate(), booking.getBookingTime());
                }
            }
            response.setId(String.valueOf(savedBooking.getId()));
            response.setStatus("success");
            return response;

        } catch (Exception e) {
            response.setStatus("Error");
            return response;
        }
    }

    @Override
    public FetchBookingResponse fetchBooking() {
        FetchBookingResponse response = new FetchBookingResponse();
        List<FlightBooking> list = repository.findAll();
        if(list.isEmpty()){
            response.setStatus("false");
            return response;
        }response.setStatus("true");
        response.setBooking(list);
    return response;
    }
}
