package com.bankBR.repository;

import com.bankBR.domains.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface Depository extends CrudRepository<Deposit, Long> {
}
