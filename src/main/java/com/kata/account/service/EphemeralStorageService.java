package com.kata.account.service;

import com.kata.account.model.Account;
import com.kata.account.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EphemeralStorageService {

  private static final Set<Account> ACCOUNTS;
  private static final Set<Transaction> TRANSACTIONS;
  DateTimeFormatter formatter;

  static {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    ACCOUNTS = new HashSet<>(Arrays.asList(
      new Account()
        .withId(UUID.randomUUID().toString())
        .withName("VALUE CONSULTING")
        .withBalance(new BigDecimal(1700))
        .withIban("5110980584")
        .withBban("TN5908003000511098058479")
        .withAccountOpeningDate(LocalDate.parse("24/09/2019", formatter)),
      new Account()
        .withId(UUID.randomUUID().toString())
        .withName("Mr MOHAMED OUNI")
        .withBalance(new BigDecimal(66000))
        .withIban("C459001390")
        .withBban("TN5908032012045900139034")
        .withAccountOpeningDate(LocalDate.parse("11/08/2016", formatter)),
      new Account()
        .withId(UUID.randomUUID().toString())
        .withName("Mme BEN AYED KHANSA")
        .withBalance(new BigDecimal(82000))
        .withIban("7120998891")
        .withBban("TN5908003000712099889116")
        .withAccountOpeningDate(LocalDate.parse("01/02/2021", formatter))
    ));
    TRANSACTIONS = new HashSet<>();

  }

  public Set<Account> getAccounts() {
    return ACCOUNTS;
  }

  public Optional<Account> getAccountById(String id) {
    return ACCOUNTS.stream().filter(account -> account.getId().equals(id)).findAny();
  }

  public String putAccount(Account updatedAccount) {
    ACCOUNTS.removeIf(account -> updatedAccount.getId().equals(account.getId()));
    ACCOUNTS.add(updatedAccount);
    return updatedAccount.getId();
  }

  public Set<Transaction> getTransactions() {
    return TRANSACTIONS;
  }

  public boolean addTransaction(Transaction transaction) {
    return TRANSACTIONS.add(transaction);
  }

}
