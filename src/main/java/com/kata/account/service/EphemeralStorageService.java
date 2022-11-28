package com.kata.account.service;

import com.kata.account.model.Account;
import com.kata.account.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class EphemeralStorageService {

    private static final Set<Account> ACCOUNTS = new HashSet<>();
    private static final Set<Transaction> TRANSACTIONS = new HashSet<>();


    public Set<Account> getAccounts() {
        return ACCOUNTS;
    }

    public String postAccount(Account account) {
        String accountId = UUID.randomUUID().toString();
        account.setId(accountId);
        ACCOUNTS.add(account);
        return accountId;
    }

    public Optional<Account> getAccountById(String id) {
        return ACCOUNTS.stream().filter(account -> account.getId().equals(id)).findAny();
    }

    public String putAccount(Account updatedAccount) {
        ACCOUNTS.removeIf(account -> updatedAccount.getId().equals(account.getId()));
        ACCOUNTS.add(updatedAccount);
        return updatedAccount.getId();
    }

    public Set<Transaction> getTransactions() {
        return TRANSACTIONS;
    }

    public boolean addTransaction(Transaction transaction) {
        return TRANSACTIONS.add(transaction);
    }

}
