package com.vti.dulichviet_team_1.modal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`tour`")
public class Tour {
  @Id
  @Column(name = "`id`")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "`title`", nullable = false)
  private String title;

  @Column(name = "`type`", nullable = false)
  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(name = "`depart`", nullable = false)
  private String depart;

  @Column(name = "`arrival`", nullable = false)
  private String arrival;

  @Column(name = "`duration`", nullable = false)
  private int duration;

  @Column(name = "`transport`", nullable = false)
  @Enumerated(EnumType.STRING)

  private Transport transport;

  @Column(name = "`content`", nullable = false)
  private String content;

  @Column(name = "`image`", nullable = false)
  private String image;

  @Column(name = "`price`", nullable = false)
  private int price;

  @Column(name = "`status`", nullable = false)
  @Enumerated(EnumType.STRING)
  private TourStatus status;

<<<<<<< HEAD
  @Column(name = "max_guest_size",nullable = false)
  private Integer maxGuestSize;
=======
  @Column(name = "`maxGuestSize`", nullable = false)
  private int maxGuestSize;
>>>>>>> 0b8b2d9e7b6a14cb67835a63328d0876d9d5b2c8
}
