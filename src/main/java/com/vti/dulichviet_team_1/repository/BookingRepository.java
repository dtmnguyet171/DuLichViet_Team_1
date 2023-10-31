package com.vti.dulichviet_team_1.Repository;

import com.vti.dulichviet_team_1.modal.dto.TourBookingCount;
import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.modal.entity.BookingStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

    //    Page<Booking> findByAccountId_EmailAndAccountId_UsernameAndAccountId_FullnameAndAccountId_PhoneAndStatus(
//            String email, String username, String fullname, String phone, String status, Pageable pageable);
    List<Booking> findByAccountId(Account account);

    List<Booking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.vti.dulichviet_team_1.modal.dto.TourBookingCount(b.tour, COUNT (b.id))"
            + "from Booking  b group by b.tour" +
            " order by count(b.id) DESC " )
    List<TourBookingCount> getMostBookedTours();

    Page<Booking> findByStatus(BookingStatus status, Pageable pageable);

}


