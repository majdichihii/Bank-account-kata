package com.kata.account.service;


import com.kata.account.exception.AccountNotFoundException;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.CreditDebitIndicator;
import com.kata.account.model.PostBalanceRequest;
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

  public String balanceDeposit(PostBalanceRequest postBalanceRequest)
    throws AccountNotFoundException {
    Account account = this.checkExistingAccount(postBalanceRequest.getAccountId());

    log.info("adding balance to account with id  = {}",
      postBalanceRequest.getAccountId());
    BigDecimal newBalance = account.getBalance().add(postBalanceRequest.getAmount());
    account.setBalance(newBalance);
    ephemeralStorageService.addTransaction(
      this.createNewTransaction(postBalanceRequest, CreditDebitIndicator.DBIT));
    log.info("Transaction with amount = {} created successfully",
      postBalanceRequest.getAmount());
    return ephemeralStorageService.putAccount(account);

  }

  public String balanceWithdrawal(PostBalanceRequest postBalanceRequest)
    throws AccountNotFoundException, InsufficientFundsException {
    Account account = this.checkExistingAccount(postBalanceRequest.getAccountId());
    if (account.getBalance().compareTo(postBalanceRequest.getAmount()) >= 0) {
      BigDecimal newBalance = account.getBalance().subtract(postBalanceRequest.getAmount());
      account.setBalance(newBalance);
      ephemeralStorageService.addTransaction(
        this.createNewTransaction(postBalanceRequest, CreditDebitIndicator.CRDT));
      log.info("Transaction with amount = {} created successfully",
        postBalanceRequest.getAmount());
      return ephemeralStorageService.putAccount(account);
    } else {
      log.error("insufficient funds in account {}",
        postBalanceRequest.getAccountId());
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

  private Transaction createNewTransaction(PostBalanceRequest postBalanceRequest,
    CreditDebitIndicator creditDebitIndicator) {
    return Transaction.builder()
      .id(UUID.randomUUID().toString())
      .accountId(postBalanceRequest.getAccountId())
      .date(LocalDate.now())
      .transactionAmount(postBalanceRequest.getAmount())
      .creditDebitIndicator(creditDebitIndicator)
      .build();
  }
}
