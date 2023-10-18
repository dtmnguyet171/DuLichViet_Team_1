package com.vti.dulichviet_team_1.service;


import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.request.BookingCreateRequest;
import com.vti.dulichviet_team_1.request.BookingSearchRequest;
import com.vti.dulichviet_team_1.request.BookingUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookingService {


    List<Booking> getAllBooking ();

    Page<Booking> search(BookingSearchRequest searchRequest);

    void createBooking (BookingCreateRequest bookingCreateRequest);

    Booking updateBooking (int bookingId, BookingUpdateRequest bookingUpdateRequest);

    void deleteBookingId (int bookingId);
    //xem lịch sử của từng Account

    List<Booking> getBookingHistoryByAccount(Account account);

//    Page<Booking> finBookings(BookingSearch bookingSearch);


//
//    Page<Booking> getAllBookings(Pageable pageable);
//
//    Page<Booking> getAllBookingsWithStatus(BookingStatus status, Pageable pageable);



}
