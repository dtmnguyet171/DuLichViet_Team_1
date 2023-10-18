package com.vti.dulichviet_team_1.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "`booking`")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingId")
    private int id;
    @Column(name = "Note", length = 500)
    private String note;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "AccountId")
    private Account accountId ;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TourId")
    private Tour tourId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BookingStatus status;

    @Column(name = "Price")
    private double price;

    @Column(name = "BookingDate")
    private LocalDate bookingDate;

    @Column(name = "GuestSize")
    private Integer guestSize;



}
