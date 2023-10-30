package com.vti.dulichviet_team_1.Repository;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Booking;
import com.vti.dulichviet_team_1.modal.entity.BookingStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

    //    Page<Booking> findByAccountId_EmailAndAccountId_UsernameAndAccountId_FullnameAndAccountId_PhoneAndStatus(
//            String email, String username, String fullname, String phone, String status, Pageable pageable);
    List<Booking> findByAccount(Account account);

    Page<Booking> findByStatus(BookingStatus status, Pageable pageable);

}


