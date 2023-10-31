package com.vti.dulichviet_team_1.service;


import com.vti.dulichviet_team_1.modal.dto.TourBookingCount;
import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.request.BookingCreateRequest;
import com.vti.dulichviet_team_1.request.BookingSearchRequest;
import com.vti.dulichviet_team_1.request.BookingUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookingService {


    List<Booking> getAllBooking();

    Page<Booking> search(BookingSearchRequest searchRequest);

    void createBooking(BookingCreateRequest bookingCreateRequest);

    Booking updateBooking(int bookingId, BookingUpdateRequest bookingUpdateRequest);

    void deleteBookingId(int bookingId);
    //xem lịch sử của từng Account

    List<Booking> getBookingHistoryByAccount(Account account);

//    Page<Booking> finBookings(BookingSearch bookingSearch);

    //tìm kiếm danh sach booking theo năm
    List<Booking> getBookingInYear(int year);

    List<Double> revenueByMonth(int year);

    //Tinh tong so tien da booking theo thang va nam
//    double manyToMonth(int year, int month);

    // tinh tour duoc booking nhieu nhat
    List<TourBookingCount> getMostBookedTours();


//
//    Page<Booking> getAllBookings(Pageable pageable);
//
//    Page<Booking> getAllBookingsWithStatus(BookingStatus status, Pageable pageable);


}
