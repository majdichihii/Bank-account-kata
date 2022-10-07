package com.kata.account.controller.impl;

import com.kata.account.controller.AccountController;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequest;
import com.kata.account.model.PostBalanceResponse;
import com.kata.account.service.AccountService;
import java.util.Set;
import javax.security.auth.login.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountControllerImpl implements AccountController {

  private final AccountService accountService;

  @Override
  public ResponseEntity<Set<Account>> getAccounts() {
    log.debug("fetching accounts ...");
    return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PostBalanceResponse> postBalanceDeposit(
    PostBalanceRequest postBalanceRequest) {
    try {
      return new ResponseEntity<>(
        new PostBalanceResponse().withId(accountService.balanceDeposit(postBalanceRequest))
        , HttpStatus.OK);
    } catch (AccountNotFoundException e) {
      log.error("Error when updating account: {} ", e.toString());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<PostBalanceResponse> postBalanceWithdrawal(
    PostBalanceRequest postBalanceRequest) {
    try {
      return new ResponseEntity<>(
        new PostBalanceResponse().withId(accountService.balanceWithdrawal(postBalanceRequest))
        , HttpStatus.OK);
    } catch (AccountNotFoundException | InsufficientFundsException e) {
      log.error("Error when updating account: {} ", e.toString());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
