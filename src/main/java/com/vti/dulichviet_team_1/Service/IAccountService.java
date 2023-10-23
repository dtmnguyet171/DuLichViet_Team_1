package com.vti.dulichviet_team_1.Service;

import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.request.AccountCreateRq;
import com.vti.dulichviet_team_1.request.AccountUpdateRq;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(int id);
    void createAccount(AccountCreateRq accountCreateRq);
    Account  updateAccount(int id,AccountUpdateRq account);
   void deleteAccount(int id);
}
