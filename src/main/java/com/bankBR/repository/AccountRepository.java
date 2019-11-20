package com.bankBR.repository;

import com.bankBR.domains.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountByCustomerId(Long customerId);
}
