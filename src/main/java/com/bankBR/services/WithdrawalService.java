package com.bankBR.services;

import com.bankBR.domains.Withdrawal;
import com.bankBR.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class WithdrawalService {

  @Autowired
  private WithdrawalRepository withdrawalRepository;


  //get all withdrawals for a specific account
  public List<Withdrawal> getAllWithdrawals(Long accountId){
    List<Withdrawal> listOfWithdrawals = new ArrayList<>();
    withdrawalRepository.findAll().forEach(listOfWithdrawals::add);

    List<Withdrawal> valid = new ArrayList<>();
    for(Withdrawal w : listOfWithdrawals){
      if(w.getPayerId() == accountId){
        valid.add(w);
      }
    }
    return valid;
  }

  //get withdrawal by id
  public Optional<Withdrawal> getWithdrawalById(Long withdrawalId){

    return withdrawalRepository.findById(withdrawalId);
  }

  //create a withdrawal
  public Withdrawal addWithdrawal(Withdrawal withdrawal, Long accountId) {
    return withdrawalRepository.save(withdrawal);
  }

  //update a specific existing withdrawal
  public Withdrawal updateWithdrawal( Withdrawal withdrawal, Long withdrawalId) {

    return withdrawalRepository.save(withdrawal);
  }

  //delete a specific existing withdrawal
  public void deleteWithdrawal(Long withdrawalId) {

    withdrawalRepository.deleteById(withdrawalId);
  }

}
