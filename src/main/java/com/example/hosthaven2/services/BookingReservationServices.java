package com.example.hosthaven2.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.BookingReservationEntity;
import com.example.hosthaven2.exception.BusinessValidationException;
import com.example.hosthaven2.exception.ResourceNotFoundException;
import com.example.hosthaven2.repository.BookingReservationRepository;

@Service
public class BookingReservationServices {

    @Autowired
    BookingReservationRepository bookingRepo;

    // Create Booking
    public BookingReservationEntity saveData(BookingReservationEntity data) {

        if (data.getCheckInDate() == null || data.getCheckOutDate() == null) {
            throw new BusinessValidationException("Check-in and Check-out dates are required");
        }

        if (data.getCheckOutDate().isBefore(data.getCheckInDate())) {
            throw new BusinessValidationException("Check-out date cannot be before check-in date");
        }

        if (data.getTotalPrice() == null) {
            throw new BusinessValidationException("Total price is required");
        }

        if (data.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("Total price must be greater than zero");
        }

        return bookingRepo.save(data);
    }

    // Get All Bookings
    public List<BookingReservationEntity> getDetailAllData() {
        return bookingRepo.findAll();
    }

    // Get Booking By ID
    public BookingReservationEntity getBookings(Long id) {

        return bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    // Update Booking
    public BookingReservationEntity updateBooking(Long id, BookingReservationEntity data) {

        BookingReservationEntity booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (data.getCheckInDate() == null || data.getCheckOutDate() == null) {
            throw new BusinessValidationException("Check-in and Check-out dates are required");
        }

        if (data.getCheckOutDate().isBefore(data.getCheckInDate())) {
            throw new BusinessValidationException("Check-out date cannot be before check-in date");
        }

        if (data.getTotalPrice() == null) {
            throw new BusinessValidationException("Total price is required");
        }

        if (data.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("Total price must be greater than zero");
        }

        // Don't update ID
        booking.setCheckInDate(data.getCheckInDate());
        booking.setCheckOutDate(data.getCheckOutDate());
        booking.setTotalPrice(data.getTotalPrice());
        booking.setIsRated(data.getIsRated());

        return bookingRepo.save(booking);
    }

    // Delete Booking
    public BookingReservationEntity getDelete(Long id) {

        BookingReservationEntity booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        bookingRepo.delete(booking);

        return booking;
    }
}
