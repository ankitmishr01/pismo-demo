package com.pismo.demo.service;


import com.pismo.demo.exception.CustomException;
import com.pismo.demo.exception.ResourceNotFoundException;
import com.pismo.demo.model.Account;
import com.pismo.demo.repository.AccountRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccountDetails(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public Account getAcccountDetails(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + accountId + " not found"));

    }

}

