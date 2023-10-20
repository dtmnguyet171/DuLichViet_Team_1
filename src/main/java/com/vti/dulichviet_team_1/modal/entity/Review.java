package com.vti.dulichviet_team_1.modal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Review")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "username", length = 100, nullable = false)
    private String userName;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "rating")
    private int rating;


}
