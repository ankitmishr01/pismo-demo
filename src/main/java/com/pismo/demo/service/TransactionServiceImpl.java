package com.pismo.demo.service;


import com.pismo.demo.exception.ResourceNotFoundException;
import com.pismo.demo.model.Transaction;
import com.pismo.demo.repository.AccountRepository;
import com.pismo.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction saveTransaction(Transaction transaction) {

        // Check if account exists
        accountRepository.findById(transaction.getAccount().getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return transactionRepository.save(transaction);
    }
}

