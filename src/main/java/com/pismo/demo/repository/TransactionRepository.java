package com.pismo.demo.repository;

import com.pismo.demo.entity.Account;
import com.pismo.demo.entity.AccountType;
import com.pismo.demo.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    // HQL
    @Query(value = "From Transaction t where t.account = :accountId and operationType = :accountType and t.balance < 0 order by t.timestamp")
    List<Transaction> findNegativeBalanceOrderByTimestamp(Account accountId, AccountType accountType);
}
