package com.kata.account.service.impl;

import com.kata.account.model.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

import com.kata.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {


    private final EphemeralStorageServiceImpl ephemeralStorageServiceImpl;

    public Set<Transaction> getTransactionsByAccountId(String accountId) {
        return ephemeralStorageServiceImpl.getTransactions()
                .stream()
                .filter(transaction -> transaction.getAccountId().equals(accountId))
                .collect(Collectors.toSet());
    }
}
