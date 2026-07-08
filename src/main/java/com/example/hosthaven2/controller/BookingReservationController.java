package com.example.hosthaven2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.example.hosthaven2.entity.BookingReservationEntity;
import com.example.hosthaven2.services.BookingReservationServices;

@RestController
public class BookingReservationController {

    @Autowired
    BookingReservationServices bookingSer;

    @PostMapping("/postBooking")
    public BookingReservationEntity saveData(@RequestBody BookingReservationEntity data) {
        return bookingSer.saveData(data);
    }

    @GetMapping("/getBooking")
    public List<BookingReservationEntity> getData() {
        return bookingSer.getDetailAllData();
    }

    @GetMapping("/getBooking/{id}")
    public BookingReservationEntity getBookingData(@PathVariable Long id) {
        return bookingSer.getBookings(id);
    }

    @PutMapping("/getBooking/{id}")
    public BookingReservationEntity updateData(@PathVariable Long id,@RequestBody BookingReservationEntity data) {
        return bookingSer.updateBooking(id, data);
    }

@DeleteMapping("/getBooking/{id}")
public BookingReservationEntity getDelete(@PathVariable Long id) {
    return bookingSer.getDelete(id);
}

}
