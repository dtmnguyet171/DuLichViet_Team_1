package com.vti.dulichviet_team_1.request;

import com.vti.dulichviet_team_1.modal.entity.Account;
import lombok.Data;

import javax.persistence.Column;

@Data
public class AccountSearchRequest extends BaseRequest {

    private String email;

    private String username;

    private String fullName;

    private String phone;
}
