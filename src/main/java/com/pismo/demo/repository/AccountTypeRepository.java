package com.pismo.demo.repository;

import com.pismo.demo.entity.AccountType;
import org.springframework.data.repository.CrudRepository;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
