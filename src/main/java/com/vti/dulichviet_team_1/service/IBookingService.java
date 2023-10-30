package com.vti.dulichviet_team_1.Service;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.request.BookingCreateRequest;
import com.vti.dulichviet_team_1.request.BookingUpdateRequest;
import com.vti.dulichviet_team_1.request.BookingsSearchRQ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookingService {


    List<Booking> getAllBooking();


    void createBooking(BookingCreateRequest bookingCreateRequest);

    Booking updateBooking(int bookingId, BookingUpdateRequest bookingUpdateRequest);

    void deleteBookingId(int bookingId);
    //xem lịch sử của từng Account

    List<Booking> getBookingHistoryByAccount(Account account);

    // chức năng search
    Page<Booking> finBookings(BookingsSearchRQ bookingsSearchRQ, Pageable pageable);


//
//    Page<Booking> getAllBookings(Pageable pageable);
//
//    Page<Booking> getAllBookingsWithStatus(BookingStatus status, Pageable pageable);


}
