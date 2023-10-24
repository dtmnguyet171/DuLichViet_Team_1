package com.vti.dulichviet_team_1.Repository;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
        boolean existsByEmail(String email);
        boolean existsByUsername(String username);
}
