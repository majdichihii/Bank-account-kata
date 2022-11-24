package com.kata.account.controller.impl;

import com.kata.account.controller.TransactionController;
import com.kata.account.model.Transaction;
import com.kata.account.service.impl.TransactionServiceImpl;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionControllerImpl implements TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    @Override
    public ResponseEntity<Set<Transaction>> getTransactionsByAccountId(String accountId) {
        log.debug("fetching transactions ...");

        return new ResponseEntity<>(transactionServiceImpl.getTransactionsByAccountId(accountId
        ), HttpStatus.OK);
    }

}
