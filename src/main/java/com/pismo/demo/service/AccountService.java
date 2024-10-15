package com.pismo.demo.service;

import com.pismo.demo.model.Account;
import org.springframework.stereotype.Service;


public interface AccountService {

    Account getAcccountDetails(Long accountId);

    Account saveAccountDetails(Account account);
}
