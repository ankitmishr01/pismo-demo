package com.pismo.demo.controller;

import com.pismo.demo.model.TransactionModel;
import com.pismo.demo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody TransactionModel transaction) {
        log.info("Creating transaction: {}", transaction);
        return ResponseEntity.ok(transactionService.saveTransaction(transaction));
    }
}
