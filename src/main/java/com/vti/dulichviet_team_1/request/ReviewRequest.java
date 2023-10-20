package com.vti.dulichviet_team_1.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequest {

    private String username;

    private String reviewText;

    private int rating;

    private int tour;
}
