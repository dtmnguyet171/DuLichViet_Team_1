package com.vti.dulichviet_team_1.Service.impl;

import com.vti.dulichviet_team_1.Repository.BookingRepository;
import com.vti.dulichviet_team_1.Repository.IAccountRepository;
import com.vti.dulichviet_team_1.Repository.TourRepository;
import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.modal.entity.BookingStatus;
import com.vti.dulichviet_team_1.modal.entity.Tour;
import com.vti.dulichviet_team_1.repository.Specification.BookingSpecification;
import com.vti.dulichviet_team_1.request.BookingCreateRequest;
import com.vti.dulichviet_team_1.request.BookingSearchRequest;
import com.vti.dulichviet_team_1.request.BookingUpdateRequest;
import com.vti.dulichviet_team_1.service.IBookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    @Autowired
    BookingRepository bookingrepository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    TourRepository tourRepository;

    @Override
    public List<Booking> getAllBooking() {
        return bookingrepository.findAll();
    }


    @Override
    public Page<Booking> search(BookingSearchRequest searchRequest) {
        PageRequest pageRequest = null;

        if ("DESC".equals(searchRequest.getSortType())) {
            pageRequest = PageRequest.of(searchRequest.getPage() - 1, searchRequest.getSize(), Sort.by(searchRequest.getSortField()).descending());
        } else {
            pageRequest = PageRequest.of(searchRequest.getPage() - 1, searchRequest.getSize(), Sort.by(searchRequest.getSortField()).ascending());

        }
        Specification<Booking> condition = BookingSpecification.buildCondition(searchRequest);

        return bookingrepository.findAll(condition, pageRequest);
    }


    @Override
    public void createBooking(BookingCreateRequest bookingCreateRequest) {
        Optional<Tour> tour = tourRepository.findById(bookingCreateRequest.getTourId());
        Optional<Account> account = accountRepository.findById(bookingCreateRequest.getAccountId());

        if (tour.isEmpty() || account.isEmpty()) {
            throw new RuntimeException("Không tìm thấy tour or account " + bookingCreateRequest.getTourId() + bookingCreateRequest.getAccountId());
        }
        if (bookingCreateRequest.getGuestSize() <= tour.get().getMaxGuestSize()) {
            Booking booking = new Booking();
            BeanUtils.copyProperties(bookingCreateRequest, booking);
            booking.setAccount(account.get());
            booking.setTour(tour.get());
            booking.setBookingDate(LocalDate.now());
            booking.setPrice(bookingCreateRequest.getPrice());
            booking.setStatus(BookingStatus.CONFIRM);
            booking.setGuestSize(bookingCreateRequest.getGuestSize());
            bookingrepository.save(booking);
            // cap nhap lai maxGroupSize
            // sau khi booking thanh cong se tru di so luong nguoi trong tour dang co
            int updateMaxGroupSize = tour.get().getMaxGuestSize() - bookingCreateRequest.getGuestSize();
            // luu lai xuong database
            tour.get().setMaxGuestSize(updateMaxGroupSize);
            tourRepository.save(tour.get());
        } else {
            throw new RuntimeException("Không thể đặt tour vì số lượng booking đã vượt quá số lượng slot còn trống ");
        }

    }


    // chỉ có thể update thông tin tuor chưa khởi hành .. nếu tour done thì không thay dổi về số lượng tour
    @Override
    public Booking updateBooking(int bookingId, BookingUpdateRequest bookingUpdateRequest) {
        Optional<Booking> bookingOptional = bookingrepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            Optional<Tour> newTour = tourRepository.findById(bookingUpdateRequest.getTourId());
            Optional<Account> account = accountRepository.findById(bookingUpdateRequest.getAccountId());
            Optional<Tour> oldTour = tourRepository.findById(booking.getTour().getId());

            if (newTour.isPresent() && account.isPresent() && oldTour.isPresent()) {
                // nếu update hủy tour thì sẽ trả lại số lượng đã booking cho tour
                if (BookingStatus.CANCEL.equals(bookingUpdateRequest.getStatus()) && bookingOptional.get().getStatus() != bookingUpdateRequest.getStatus()) {
                    oldTour.get().setMaxGuestSize(oldTour.get().getMaxGuestSize() + bookingOptional.get().getGuestSize());
                    tourRepository.save(oldTour.get());
                }

                // Kiểm tra xem tour mới và tour cũ có giống nhau hay không
                if (newTour.get().getId() != oldTour.get().getId()) {
                    // Trả lại số lượng booking tour cũ
                    oldTour.get().setMaxGuestSize(oldTour.get().getMaxGuestSize() + bookingOptional.get().getGuestSize());
                    tourRepository.save(oldTour.get());

                    // Trừ đi số lượng tour mới hiện có nếu booking có status là confirm nếu tour là done thì không thay đổi
                    if (BookingStatus.CONFIRM.equals(bookingUpdateRequest.getStatus())) {
                        newTour.get().setMaxGuestSize(newTour.get().getMaxGuestSize() - bookingUpdateRequest.getGuestSize());
                        tourRepository.save(newTour.get());
                    }
                }

                BeanUtils.copyProperties(bookingUpdateRequest, booking);

                booking.setAccount(account.get());
                booking.setTour(newTour.get());
                booking.setStatus(bookingUpdateRequest.getStatus());
                booking.setGuestSize(bookingUpdateRequest.getGuestSize());
                bookingrepository.save(booking);

                return booking;
            } else {
                throw new RuntimeException("Không tìm thấy tour hoặc tài khoản");
            }
        } else {
            throw new RuntimeException("Không tìm thấy booking id " + bookingId);
        }
    }

    @Override
    public void deleteBookingId(int bookingId) {
        Optional<Booking> booking = bookingrepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingrepository.deleteById(bookingId);
        } else {
            throw new RuntimeException("Booking ban dang muon xoa khong ton tai");
        }
    }

    //  - Xem lịch sử booking của từng account
    @Override
    public List<Booking> getBookingHistoryByAccount(Account account) {
        return bookingrepository.findByAccountId(account);
    }


//    @Override
//    public Page<Booking> finBookings( BookingSearch bookingSearch) {
//        Pageable pageable = PageRequest.of(bookingSearch.getPage(),bookingSearch.getSize(), Sort.by(bookingSearch.getSort()));
//
//        return bookingrepository.findByAccountId_EmailAndAccountId_UsernameAndAccountId_FullnameAndAccountId_PhoneAndStatus(bookingSearch.getEmail(),
//                bookingSearch.getUsername(),bookingSearch.getFullname(),bookingSearch.getPhone(),bookingSearch.getStatus(),pageable
//        );
//    }


//
//    @Override
//    public Page<Booking> getAllBookings(Pageable pageable) {
//        return bookingrepository.findAll(pageable);
//
//    }
//
//    @Override
//    public Page<Booking> getAllBookingsWithStatus(BookingStatus status, Pageable pageable) {
//        return bookingrepository.findByStatus(status, pageable);
//    }
}







