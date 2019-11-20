package com.bankBR.controller;

import com.bankBR.domains.Account;
import com.bankBR.services.AccountService;
import com.bankBR.domains.response.ResponseReturn;
import com.bankBR.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<?> getAllAccts(Account accounts){
        List<Account> allAccts = accountService.getAllAccounts(accounts);
        ResponseReturn rep = new ResponseReturn();
        if(!allAccts.isEmpty()){
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(allAccts);
            return new ResponseEntity<>(rep,HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }


    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        ResponseReturn rep = new ResponseReturn();
        Optional<Account> acctt = accountService.getById(id);
        if(acctt.isPresent()){
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(acctt);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
        }



    }
  
    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}/accounts")
    public ResponseEntity<?> getAcctForCust(@PathVariable Long id){

        List<Account> CustomerAccts =  accountService.getAllCustomerAccounts(id);
        ResponseReturn rep = new ResponseReturn();
        if(!CustomerAccts.isEmpty()){
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(CustomerAccts);
            return new ResponseEntity<>(rep,HttpStatus.OK);
        }else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{id}/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account, @PathVariable Long id)
    {
        Account a = accountService.addAccount(account,id);
        if(a != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newAcctUri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(a.getId())
                    .toUri();
            responseHeaders.setLocation(newAcctUri);
            return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account a = accountService.updateAccount(account, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    public ResponseEntity<?> delAccount(@RequestBody Long id){
      accountService.delAccount(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }

}
