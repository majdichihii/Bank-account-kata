package com.kata.account.controller.impl;

import com.kata.account.controller.AccountController;
import com.kata.account.exception.AccountNotFoundException;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.mapper.PostAccountRequestBodyAccountMapper;
import com.kata.account.model.*;
import com.kata.account.service.impl.AccountServiceImpl;
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
public class AccountControllerImpl implements AccountController {

    private final AccountServiceImpl accountServiceImpl;
    private final PostAccountRequestBodyAccountMapper postAccountRequestBodyAccountMapper;

    @Override
    public ResponseEntity<PostAccountResponseBody> PostAccounts(PostAccountRequestBody postAccountRequestBody) {
        log.debug("creating a new account ...");
        return new ResponseEntity<>(PostAccountResponseBody
                .builder()
                .id(accountServiceImpl.postAccount(postAccountRequestBodyAccountMapper.PostAccountRequestToAccount(postAccountRequestBody)))
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<Account>> getAccounts() {
        log.debug("fetching accounts ...");
        return new ResponseEntity<>(accountServiceImpl.getAccounts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostBalanceResponseBody> postBalanceDeposit(
            PostBalanceRequestBody postBalanceRequestBody) {
        try {
            return new ResponseEntity<>(
                    PostBalanceResponseBody.builder()
                            .id(accountServiceImpl.balanceDeposit(postBalanceRequestBody))
                            .build()
                    , HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            log.error("Error when updating account: {} ", e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<PostBalanceResponseBody> postBalanceWithdrawal(
            PostBalanceRequestBody postBalanceRequestBody) {
        try {
            return new ResponseEntity<>(
                    PostBalanceResponseBody.builder()
                            .id(accountServiceImpl.balanceWithdrawal(postBalanceRequestBody))
                            .build()
                    , HttpStatus.OK);
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            log.error("Error when updating account: {} ", e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
