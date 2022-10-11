package com.kata.account.controller;

import com.kata.account.model.Account;
import com.kata.account.model.Transaction;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TransactionController {

  @ApiOperation(value = "Fetches all Transactions", response = Transaction.class ,  responseContainer = "Set")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Transaction items. transactions retrieved from an ephemeral storage", response = Account.class, responseContainer = "Set"),
    @ApiResponse(code = 400, message = "BadRequest"),
    @ApiResponse(code = 404, message = "NotFound"),
    @ApiResponse(code = 500, message = "InternalServerError")
  })
  @RequestMapping(
    value = {"/api/v1/transactions"},
    produces = {"application/json"},
    method = {RequestMethod.GET}
  )
  ResponseEntity<Set<Transaction>> getTransactions();
}
