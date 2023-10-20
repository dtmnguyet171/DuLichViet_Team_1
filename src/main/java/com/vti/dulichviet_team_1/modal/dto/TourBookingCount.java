package com.vti.dulichviet_team_1.modal.dto;

import com.vti.dulichviet_team_1.modal.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourBookingCount {
    private Tour tour;
    private long bookingCount;
}
