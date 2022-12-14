package com.kata.account.controller;


import com.kata.account.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

public interface AccountController {

    @ApiOperation(value = "post account", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account id. return account id", response = Account.class),
            @ApiResponse(code = 400, message = "BadRequest"),
            @ApiResponse(code = 404, message = "NotFound"),
            @ApiResponse(code = 500, message = "InternalServerError")
    })
    @RequestMapping(
            value = {"/api/v1/postAccount"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    ResponseEntity<PostAccountResponseBody> PostAccounts(@Valid @NotNull @RequestBody PostAccountRequestBody postAccountRequestBody);

    @ApiOperation(value = "Fetches all account", response = Account.class, responseContainer = "Set")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account items. Accounts retrieved from an ephemeral storage", response = Account.class, responseContainer = "Set"),
            @ApiResponse(code = 400, message = "BadRequest"),
            @ApiResponse(code = 404, message = "NotFound"),
            @ApiResponse(code = 500, message = "InternalServerError")
    })
    @RequestMapping(
            value = {"/api/v1/getAccounts"},
            produces = {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<Set<Account>> getAccounts();

    @ApiOperation(value = "Make a deposit in an account", response = PostBalanceResponseBody.class)
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
    ResponseEntity<PostBalanceResponseBody> postBalanceDeposit(@Valid @NotNull @RequestBody
                                                               PostBalanceRequestBody postBalanceRequestBody);


    @ApiOperation(value = "Make a withdrawal from my account", response = PostBalanceResponseBody.class)
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
    ResponseEntity<PostBalanceResponseBody> postBalanceWithdrawal(@Valid @NotNull @RequestBody
                                                                  PostBalanceRequestBody postBalanceRequestBody);

}
