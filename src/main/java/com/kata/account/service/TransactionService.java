package com.kata.account.service;

import com.kata.account.model.Transaction;

import java.util.Set;

public interface TransactionService {

     Set<Transaction> getTransactionsByAccountId(String accountId);
}
