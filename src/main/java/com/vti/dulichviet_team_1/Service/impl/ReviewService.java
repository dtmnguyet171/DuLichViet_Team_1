package com.vti.dulichviet_team_1.Service.impl;

import com.vti.dulichviet_team_1.Repository.ReviewRepository;
import com.vti.dulichviet_team_1.Repository.TourRepository;
import com.vti.dulichviet_team_1.modal.entity.Review;
import com.vti.dulichviet_team_1.modal.entity.Tour;
import com.vti.dulichviet_team_1.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TourRepository tourRepository;


    // phương thức để lưu đánh giá vào cơ sở dữ liệu
    public void getReviewTour(ReviewRequest reviewRequest) {

        Optional <Tour> tour = tourRepository.findById(reviewRequest.getTour());
        if (tour.isPresent()) {
            Review review = new Review();

            review.setTour(tour.get());
            review.setUserName(reviewRequest.getUsername());
            review.setReviewText(reviewRequest.getReviewText());
            review.setRating(reviewRequest.getRating());
            reviewRepository.save(review);
        }

    }


}
