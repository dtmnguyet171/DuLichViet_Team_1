package com.vti.dulichviet_team_1.Controller;

import com.vti.dulichviet_team_1.Service.impl.ReviewService;
import com.vti.dulichviet_team_1.Service.impl.TourService;
import com.vti.dulichviet_team_1.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @Autowired
    private TourService tourService;


    @PostMapping("/create-review")
    public ResponseEntity<?> getReviewTour (
          @RequestBody ReviewRequest reviewRequest
    ) {
        reviewService.getReviewTour(reviewRequest);
        return ResponseEntity.ok("Bạn đã thêm đánh giá về Tour này");
    }
}
