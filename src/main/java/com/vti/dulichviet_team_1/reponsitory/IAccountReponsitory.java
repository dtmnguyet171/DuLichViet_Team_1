package com.vti.dulichviet_team_1.reponsitory;

import com.vti.dulichviet_team_1.modal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountReponsitory extends JpaRepository<Account, Integer> {
}
