package com.kata.account.service;

import com.kata.account.exception.AccountNotFoundException;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequest;

import java.util.Set;

public interface AccountService {
    Set<Account> getAccounts();

    String postAccount(Account account);

    String balanceDeposit(PostBalanceRequest postBalanceRequest)
            throws AccountNotFoundException;

    String balanceWithdrawal(PostBalanceRequest postBalanceRequest)
            throws AccountNotFoundException, InsufficientFundsException;
}
