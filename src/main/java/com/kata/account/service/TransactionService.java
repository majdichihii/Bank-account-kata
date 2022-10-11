package com.kata.account.service;

import com.kata.account.model.Transaction;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

  private final EphemeralStorageService ephemeralStorageService;

  public Set<Transaction> getTransactions() {
    return ephemeralStorageService.getTransactions();
  }
}
