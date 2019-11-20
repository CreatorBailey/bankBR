package com.bankBR.controller;

import com.bankBR.domains.Withdrawal;
import com.bankBR.domains.response.ResponseReturn;
import com.bankBR.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class WithdrawalController {

  @Autowired
  private WithdrawalService withdrawalService;



  @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals")
  public ResponseEntity<?>  getAllWithdrawals(@PathVariable Long accountId){
    List<Withdrawal> w = withdrawalService.getAllWithdrawals(accountId);
    ResponseReturn rep = new ResponseReturn();

    if(!w.isEmpty())
    {
      rep.setCode(HttpStatus.OK.value());
      rep.setMessage("Success");
      rep.setData(w);
      return new ResponseEntity<>(rep, HttpStatus.OK);
    }
    else
    {
      rep.setCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
    }
  }



  @RequestMapping(method = RequestMethod.GET, value = "/withdrawals/{withdrawalId}")
  public ResponseEntity<?> getWithdrawal(@PathVariable Long withdrawalId){
    Optional<Withdrawal> w = withdrawalService.getWithdrawalById(withdrawalId);
    if(w.isPresent()) {
      return new ResponseEntity<>(w, HttpStatus.OK);
    }else {
      return new ResponseEntity<>("Message: Error fetching Withdrawal", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



  @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
  public ResponseEntity<?> addDeposit(@RequestBody Withdrawal deposit, @PathVariable Long accountId){
    Withdrawal w = withdrawalService.addWithdrawal(deposit, accountId);
    if(w != null){
      HttpHeaders responseHeaders = new HttpHeaders();
      URI newPollUri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(w.getId())
              .toUri();
      responseHeaders.setLocation(newPollUri);
      return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    else
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }



  @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
  public ResponseEntity<?> updateWithdrawal( @RequestBody Withdrawal withdrawal, @PathVariable Long withdrawalId){
   Withdrawal updateWithdraw = withdrawalService.updateWithdrawal(withdrawal, withdrawalId);
    return new ResponseEntity<>(HttpStatus.OK);
  }



  @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
  public ResponseEntity<?> deleteWithdrawal(@PathVariable Long withdrawalId) {
    withdrawalService.deleteWithdrawal(withdrawalId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
