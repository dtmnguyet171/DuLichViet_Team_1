package com.vti.dulichviet_team_1.modal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`account`")
public class Account {
  @Id
  @Column(name = "`id`")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "`email`")
  private String email;

  @Column(name = "`username`")
  private String username;

  @Column(name = "`full_name`")
  private String fullName;

  @Column(name = "`phone`")
  private String phone;

  @Column(name = "`address`")
  private String address;

  @Column(name = "`password`")
  private String password;

  @Column(name = "`role`")
  private Role role;

  @Column(name = "`status`")
  private AccountStatus status;
}
