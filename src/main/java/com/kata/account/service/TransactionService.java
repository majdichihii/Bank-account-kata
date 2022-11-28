package com.kata.account.service;

import com.kata.account.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {


    private final EphemeralStorageService ephemeralStorageService;

    public Set<Transaction> getTransactionsByAccountId(String accountId) {
        return ephemeralStorageService.getTransactions()
                .stream()
                .filter(transaction -> transaction.getAccountId().equals(accountId))
                .collect(Collectors.toSet());
    }
}
