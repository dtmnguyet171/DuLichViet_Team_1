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

  @Column(name = "`title`")
  private String title;

  @Column(name = "`type`")
  private Type type;

  @Column(name = "`depart`")
  private String depart;

  @Column(name = "`arrival`")
  private String arrival;

  @Column(name = "`duration`")
  private int duration;

  @Column(name = "`transport`")
  private Transport transport;

  @Column(name = "`content`")
  private String content;

  @Column(name = "`image`")
  private String image;

  @Column(name = "`price`")
  private int price;

  @Column(name = "`status`")
  private TourStatus status;
}
