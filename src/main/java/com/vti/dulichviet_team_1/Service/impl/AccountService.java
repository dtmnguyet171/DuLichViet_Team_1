package com.vti.dulichviet_team_1.Service.impl;

import com.vti.dulichviet_team_1.Repository.IAccountRepository;
import com.vti.dulichviet_team_1.Repository.specification.AccountSpecification;
import com.vti.dulichviet_team_1.Service.IAccountService;
import com.vti.dulichviet_team_1.modal.entity.Account;
import com.vti.dulichviet_team_1.modal.entity.AccountStatus;
import com.vti.dulichviet_team_1.modal.entity.Role;
import com.vti.dulichviet_team_1.request.AccountCreateRq;
import com.vti.dulichviet_team_1.request.AccountSearchRequest;
import com.vti.dulichviet_team_1.request.AccountUpdateRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;


    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Page<Account> getAllAccounts(AccountSearchRequest request) {
        PageRequest pageRequest = null;

        if ("DESC".equals(request.getSortType())) {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).descending());

        } else {
            pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), Sort.by(request.getSortField()).ascending());
        }
        Specification<Account> condition = AccountSpecification.buildCondition(request);
        System.out.println(condition);
        return accountRepository.findAll(condition, pageRequest);
    }

    @Override
    public Optional<Account> getAccountById(int id) {

        return accountRepository.findById(id);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createAccount(AccountCreateRq accountCreateRq) {
        if (accountRepository.existsByEmail(accountCreateRq.getEmail())){
            throw new RuntimeException("Email da ton tai");
        }
        if (accountRepository.existsByUsername(accountCreateRq.getUsername())){
            throw new RuntimeException("Username da ton tai");
        }
        Account account = new Account();
        account.setAddress(accountCreateRq.getAddress());
        account.setEmail(accountCreateRq.getEmail());
        account.setPhone(accountCreateRq.getPhone());
        account.setFullName(accountCreateRq.getFullName());
        account.setUsername(accountCreateRq.getUsername());
        account.setRole(Role.USER);
        account.setStatus(AccountStatus.INACTIVE);
        account.setPassword(accountCreateRq.getPassword());
        accountRepository.save(account);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Account updateAccount(int id,AccountUpdateRq accountUpdateRq) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("khong tim thay account"));

        account.setAddress(accountUpdateRq.getAddress());
        account.setEmail(accountUpdateRq.getEmail());
        account.setPhone(accountUpdateRq.getPhone());
        account.setFullName(accountUpdateRq.getFullName());
        account.setUsername(accountUpdateRq.getUsername());
        account.setRole(accountUpdateRq.getRole());
        account.setStatus(accountUpdateRq.getStatus());
        account.setPassword(accountUpdateRq.getPassword());

        return  accountRepository.save(account);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteAccount(int id) {
    Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isPresent()){
        accountRepository.deleteById(id);
    }else {
      throw new RuntimeException("khong tim thay account");
    }
    }
}
