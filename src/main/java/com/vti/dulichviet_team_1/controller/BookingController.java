package com.vti.dulichviet_team_1.Controller;


import com.vti.dulichviet_team_1.Service.impl.BookingService;
import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;

import com.vti.dulichviet_team_1.request.BookingCreateRequest;
import com.vti.dulichviet_team_1.request.BookingSearchRequest;
import com.vti.dulichviet_team_1.request.BookingUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @GetMapping("/get-all-booking")
    public ResponseEntity<List<Booking>> getAllBooking() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBooking());
    }

    @PostMapping("/search")
    public Page<Booking> search(@RequestBody BookingSearchRequest request) {
        return bookingService.search(request);
    }

    // thêm mới Booking
    @PostMapping("/create-booking")
    public ResponseEntity<?> createBooking(BookingCreateRequest bookingCreateRequest) {
        bookingService.createBooking(bookingCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // sửa booking
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody BookingUpdateRequest bookingRequest) {
        try {
            Booking updatedBooking = bookingService.updateBooking(bookingId, bookingRequest);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // xóa booking
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable int bookingId) {
        bookingService.deleteBookingId(bookingId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public Page<Booking> viewBookings(@RequestBody BookingSearch bookingSearch){
//        return bookingService.finBookings(bookingSearch);
//    }

    // tìm dang sách booking từng account
    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Integer accountId) {
        Account account = new Account();
        account.setId(accountId);
        List<Booking> bookingHistory = bookingService.getBookingHistoryByAccount(account);
        return ResponseEntity.ok(bookingHistory);
    }


//    // filter Theo status and phân trang
//    @GetMapping("/history")
//    public ResponseEntity<Page<Booking>> getAllBookings(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) BookingStatus status) {
//
//        Pageable pageable = PageRequest.of(page, size, Sort.by("bookingDate").descending());
//
//        Page<Booking> bookings;
//
//        if (status != null) {
//            bookings = bookingService.getAllBookingsWithStatus(status, pageable);
//        } else {
//            bookings = bookingService.getAllBookings(pageable);
//        }
//
//        return ResponseEntity.ok(bookings);
//    }

}
