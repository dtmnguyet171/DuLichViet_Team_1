package com.vti.dulichviet_team_1.request;

import com.vti.dulichviet_team_1.modal.entity.BookingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingSearchRequest {
  private String username;
  private String fullName;
  private String phone;
  private String email;
  private BookingStatus status ;
}
