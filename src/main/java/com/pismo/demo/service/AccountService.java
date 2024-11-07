package com.pismo.demo.service;

import com.pismo.demo.model.AccountModel;


public interface AccountService {

    AccountModel getAcccountDetails(Long accountId);

    AccountModel saveAccountDetails(AccountModel account);
}
