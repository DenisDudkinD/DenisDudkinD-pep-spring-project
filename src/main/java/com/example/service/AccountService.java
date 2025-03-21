package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
}
    public Account registerAccount(Account account){
        if(accountRepository.findByUsername(account.getUsername()) == null){
        return accountRepository.save(account);
    }
    return null;
}
    public Account loginAccount(String username, String password){
     return accountRepository.findByUsernameAndPassword(username,password);
    }
    public Account findByID(Integer id){
        return accountRepository.findById(id).orElse(null);
    }
}
