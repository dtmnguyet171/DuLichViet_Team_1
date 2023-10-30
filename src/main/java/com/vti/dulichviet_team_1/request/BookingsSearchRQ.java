package com.vti.dulichviet_team_1.request;

import com.vti.dulichviet_team_1.modal.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsSearchRQ {
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private BookingStatus status ;
}
