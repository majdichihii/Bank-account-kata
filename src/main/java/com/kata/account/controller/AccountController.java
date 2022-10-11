package com.kata.account.controller;


import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequest;
import com.kata.account.model.PostBalanceResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AccountController {

  @ApiOperation(value = "Fetches all account", response = Account.class, responseContainer = "Set")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Account items. Accounts retrieved from an ephemeral storage", response = Account.class, responseContainer = "Set"),
    @ApiResponse(code = 400, message = "BadRequest"),
    @ApiResponse(code = 404, message = "NotFound"),
    @ApiResponse(code = 500, message = "InternalServerError")
  })
  @RequestMapping(
    value = {"/api/v1/account"},
    produces = {"application/json"},
    method = {RequestMethod.GET}
  )
  ResponseEntity<Set<Account>> getAccounts();


  @ApiOperation(value = "Make a deposit in an account", response = PostBalanceResponse.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Post balance, for the provided accountId", response = Account.class, responseContainer = "Set"),
    @ApiResponse(code = 400, message = "BadRequest"),
    @ApiResponse(code = 404, message = "NotFound"),
    @ApiResponse(code = 500, message = "InternalServerError")
  })
  @RequestMapping(value = "/api/v1/account/deposit",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST)
  ResponseEntity<PostBalanceResponse> postBalanceDeposit(@Valid @NotNull @RequestBody
    PostBalanceRequest postBalanceRequest);


  @ApiOperation(value = "Make a withdrawal from my account", response = PostBalanceResponse.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "withdraw balance, for the provided accountId", response = Account.class, responseContainer = "Set"),
    @ApiResponse(code = 400, message = "BadRequest"),
    @ApiResponse(code = 404, message = "NotFound"),
    @ApiResponse(code = 500, message = "InternalServerError")
  })
  @RequestMapping(value = "/api/v1/account/withdrawal",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST)
  ResponseEntity<PostBalanceResponse> postBalanceWithdrawal(@Valid @NotNull @RequestBody
    PostBalanceRequest postBalanceRequest);

}
