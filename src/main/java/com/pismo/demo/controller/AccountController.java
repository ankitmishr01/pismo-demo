package com.pismo.demo.controller;

import com.pismo.demo.model.AccountModel;
import com.pismo.demo.service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountModel> createAccount(@Valid @RequestBody AccountModel account) {
        AccountModel savedAccount = accountService.saveAccountDetails(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountModel> getAccount(@PathVariable Long accountId) {
        AccountModel account = accountService.getAcccountDetails(accountId);
        return ResponseEntity.ok(account);
    }
}
