package com.bankBR.repository;

import com.bankBR.domains.Bills;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bills, Long> {
    List<Bills> findAccountById(Long accountId);
    List<Bills> findCustomerById(Long customerId);
}
