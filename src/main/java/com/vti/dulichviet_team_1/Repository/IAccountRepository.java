package com.vti.dulichviet_team_1.Repository;

import com.vti.dulichviet_team_1.modal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
        boolean existsByEmail(String email);
        boolean existsByUsername(String username);
}
