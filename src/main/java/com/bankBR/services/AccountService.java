package com.bankBR.services;


import com.bankBR.domains.Account;


import com.bankBR.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private AccountService accountService;

// Get All Customer Accounts
    public List<Account> getAllCustomerAccounts(Long id){
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAccountByCustomerId(id).forEach(accountList::add);
        return accountList;
    }

    // Get Accounts By ID
    public Optional<Account> getById(Long id){
        return accountRepository.findById(id);
    }


    //Create Account
    public Account addAccount(Account account, Long id){
        return accountRepository.save(account);
    }
    //Update Account
    public Account updateAccount(Account account, Long id){
        return accountRepository.save(account);
    }

    // Delete By Id
    public void delAccount(Long id){
        accountRepository.deleteById(id);
    }


    public List<Account> getAllAccounts(Account account) {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }
}
