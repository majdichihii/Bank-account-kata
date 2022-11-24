package com.kata.account.service;

import com.kata.account.model.Account;
import com.kata.account.model.Transaction;

import java.util.Optional;
import java.util.Set;

public interface EphemeralStorageService {
    Set<Account> getAccounts();
    String postAccount(Account account);
    Optional<Account> getAccountById(String id);
    String putAccount(Account updatedAccount);
    Set<Transaction> getTransactions();
    boolean addTransaction(Transaction transaction);
}
