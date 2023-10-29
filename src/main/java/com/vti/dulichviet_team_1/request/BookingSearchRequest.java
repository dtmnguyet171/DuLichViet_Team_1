package com.vti.dulichviet_team_1.request;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.BookingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class BookingSearchRequest extends BaseRequest {
  private String email;

  private String username;

  private String fullName;

  private String phone;

  private Set<BookingStatus> status;
}
