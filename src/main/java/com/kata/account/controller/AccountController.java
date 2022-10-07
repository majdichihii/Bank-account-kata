package com.kata.account.controller;


import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequest;
import com.kata.account.model.PostBalanceResponse;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AccountController {

  @RequestMapping(
    value = {"/api/v1/account"},
    produces = {"application/json"},
    method = {RequestMethod.GET}
  )
  ResponseEntity<Set<Account>> getAccounts();

  @RequestMapping(value = "/api/v1/account/deposit",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST)
  ResponseEntity<PostBalanceResponse> postBalanceDeposit(@Valid @NotNull @RequestBody
    PostBalanceRequest postBalanceRequest);

  @RequestMapping(value = "/api/v1/account/withdrawal",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST)
  ResponseEntity<PostBalanceResponse> postBalanceWithdrawal(@Valid @NotNull @RequestBody
    PostBalanceRequest postBalanceRequest);

}
