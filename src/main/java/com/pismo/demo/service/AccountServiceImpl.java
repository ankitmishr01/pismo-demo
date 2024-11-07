package com.pismo.demo.service;


import com.pismo.demo.entity.Account;
import com.pismo.demo.exception.ResourceNotFoundException;
import com.pismo.demo.model.AccountModel;
import com.pismo.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountModel saveAccountDetails(AccountModel account) {

        Account account1= new Account();
        account1.setDocumentNumber(account.getDocumentNumber());

        account1 = accountRepository.save(account1);
        account.setAccountId(account1.getAccountId());

        return account;
    }


    @Override
    public AccountModel getAcccountDetails(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + accountId + " not found"));

        AccountModel accountModel = new AccountModel();
        accountModel.setDocumentNumber(accountModel.getDocumentNumber());
        accountModel.setAccountId(account.getAccountId());

        return accountModel;
    }

}

