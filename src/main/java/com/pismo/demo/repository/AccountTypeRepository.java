package com.pismo.demo.repository;

import com.pismo.demo.model.AccountType;
import com.pismo.demo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
