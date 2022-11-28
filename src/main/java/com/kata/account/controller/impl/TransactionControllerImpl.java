package com.kata.account.controller.impl;

import com.kata.account.controller.TransactionController;
import com.kata.account.model.Transaction;
import com.kata.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Set<Transaction>> getTransactionsByAccountId(String accountId) {
        log.debug("fetching transactions ...");

        return new ResponseEntity<>(transactionService.getTransactionsByAccountId(accountId
        ), HttpStatus.OK);
    }

}
