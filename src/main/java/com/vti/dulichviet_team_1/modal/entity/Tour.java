package com.vti.dulichviet_team_1.modal.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "TOUR")
@Data
public class Tour {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TOURID")
  private int id;

  @Column(name = "TITLE", length = 200, nullable = false)
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(name = "TYPE")
  private Type type;

  @Column(name = "DEPART", length = 50, nullable = false)
  private String depart;

  @Column(name = "ARRIVAL", length = 50, nullable = false)
  private  String arrival;

  @Column(name = "DURATION",nullable = false)
  private int duration;

  @Enumerated(EnumType.STRING)
  @Column(name = "TRANSPORT", nullable = false)
  private Transport transport;

  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "IMAGE", length = 500, nullable = false)
  private String image;

  @Column(name = "PRICE", nullable = false)
  private int price;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", columnDefinition = "AVAILABLE")
  private TourStatus status;

  @Column(name = "max_guest_size",nullable = false)
  private Integer maxGuestSize;



}
