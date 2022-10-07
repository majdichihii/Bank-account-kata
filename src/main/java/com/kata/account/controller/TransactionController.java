package com.kata.account.controller;

import com.kata.account.model.Transaction;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TransactionController {


  @RequestMapping(
    value = {"/api/v1/transactions"},
    produces = {"application/json"},
    method = {RequestMethod.GET}
  )
  ResponseEntity<Set<Transaction>> getTransactions();
}
