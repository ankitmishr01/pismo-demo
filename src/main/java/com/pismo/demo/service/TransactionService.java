package com.pismo.demo.service;

import com.pismo.demo.model.Transaction;
import org.springframework.stereotype.Service;


public interface TransactionService {

    Transaction saveTransaction(Transaction transaction);
}
