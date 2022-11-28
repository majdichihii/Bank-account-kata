package com.kata.account.service;


import com.kata.account.exception.AccountNotFoundException;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.CreditDebitIndicator;
import com.kata.account.model.PostBalanceRequestBody;
import com.kata.account.model.Transaction;
import com.kata.account.utils.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

    private final EphemeralStorageService ephemeralStorageService;

    public Set<Account> getAccounts() {
        return ephemeralStorageService.getAccounts();
    }

    public String postAccount(Account account) {
        return ephemeralStorageService.postAccount(account);
    }

    public String balanceDeposit(PostBalanceRequestBody postBalanceRequestBody)
            throws AccountNotFoundException {
        Account account = this.checkExistingAccount(postBalanceRequestBody.getAccountId());

        log.info("adding balance to account with id  = {}",
                postBalanceRequestBody.getAccountId());
        BigDecimal newBalance = account.getBalance().add(postBalanceRequestBody.getAmount());
        account.setBalance(newBalance);
        ephemeralStorageService.addTransaction(
                this.createNewTransaction(postBalanceRequestBody, CreditDebitIndicator.DBIT));
        log.info("Transaction with amount = {} created successfully",
                postBalanceRequestBody.getAmount());
        return ephemeralStorageService.putAccount(account);

    }

    public String balanceWithdrawal(PostBalanceRequestBody postBalanceRequestBody)
            throws AccountNotFoundException, InsufficientFundsException {
        Account account = this.checkExistingAccount(postBalanceRequestBody.getAccountId());
        if (account.getBalance().compareTo(postBalanceRequestBody.getAmount()) >= 0) {
            BigDecimal newBalance = account.getBalance().subtract(postBalanceRequestBody.getAmount());
            account.setBalance(newBalance);
            ephemeralStorageService.addTransaction(
                    this.createNewTransaction(postBalanceRequestBody, CreditDebitIndicator.CRDT));
            log.info("Transaction with amount = {} created successfully",
                    postBalanceRequestBody.getAmount());
            return ephemeralStorageService.putAccount(account);
        } else {
            log.error("insufficient funds in account {}",
                    postBalanceRequestBody.getAccountId());
            throw new InsufficientFundsException(Constants.INSUFFICIENT_FUNDS_ERROR_MESSAGE);
        }
    }

    private Account checkExistingAccount(String accountId) throws AccountNotFoundException {
        Account account = ephemeralStorageService.getAccountById(accountId).orElse(null);
        if (account != null) {
            return account;
        } else {
            log.error("account with provided id = {} does not exist", accountId);
            throw new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private Transaction createNewTransaction(PostBalanceRequestBody postBalanceRequestBody,
                                             CreditDebitIndicator creditDebitIndicator) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId(postBalanceRequestBody.getAccountId())
                .date(LocalDate.now())
                .transactionAmount(postBalanceRequestBody.getAmount())
                .creditDebitIndicator(creditDebitIndicator)
                .build();
    }
}
