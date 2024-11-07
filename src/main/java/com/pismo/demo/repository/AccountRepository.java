package com.pismo.demo.repository;

import com.pismo.demo.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
