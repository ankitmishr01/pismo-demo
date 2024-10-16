package com.pismo.demo.service;


import com.pismo.demo.entity.Account;
import com.pismo.demo.entity.AccountType;
import com.pismo.demo.entity.Transaction;
import com.pismo.demo.exception.ResourceNotFoundException;
import com.pismo.demo.model.TransactionModel;
import com.pismo.demo.repository.AccountRepository;
import com.pismo.demo.repository.AccountTypeRepository;
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

    @Autowired
    AccountTypeRepository accountTypeRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionModel saveTransaction(TransactionModel transaction) {

        // Check if account exists
        Account account = accountRepository.findById(transaction.getAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // Check if account type exists
        AccountType accountType = accountTypeRepository.findById(transaction.getOperationType())
                .orElseThrow(() -> new ResourceNotFoundException("Account Type not found"));


        Transaction transaction1 = new Transaction();
        transaction1.setAccount(account);
        transaction1.setAmount(transaction.getAmount());
        transaction1.setOperationType(accountType);

        transaction1 = transactionRepository.save(transaction1);

        transaction.setTransactionId(transaction1.getTransactionId());
        transaction.setTimestamp(transaction1.getTimestamp());
        return transaction;
    }
}

