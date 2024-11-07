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

import java.util.List;

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

        if (4 == transaction.getOperationType()) {
            double balanceLeft = transaction1.getAmount();
            // fetching all debit operation types - currently 1 type is there.
            List<Transaction> notSettled = transactionRepository.findNegativeBalanceOrderByTimestamp(account,accountTypeRepository.findById(1l).get());

            for (Transaction t : notSettled) {
                if (Math.abs(t.getBalance()) <= balanceLeft && balanceLeft > 0) {
                    balanceLeft = balanceLeft - Math.abs(t.getBalance());
                    t.setBalance(0.0);
                } else {
                    t.setBalance(t.getBalance() + balanceLeft);
                    balanceLeft = 0;
                }

                transactionRepository.save(t);

                if (balanceLeft == 0) {
                    break;
                }
            }
            transaction1.setBalance(balanceLeft);
        } else {
            transaction1.setBalance(-transaction1.getAmount());
        }

        transaction1 = transactionRepository.save(transaction1);

        transaction.setTransactionId(transaction1.getTransactionId());
        transaction.setTimestamp(transaction1.getTimestamp());
        transaction.setBalance(transaction1.getBalance());
        return transaction;
    }
}

