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
      Account.builder()
        .id(UUID.randomUUID().toString())
        .name("VALUE CONSULTING")
        .balance(new BigDecimal(1700))
        .iban("5110980584")
        .bban("TN5908003000511098058479")
        .accountOpeningDate(LocalDate.parse("24/09/2019", formatter))
        .build(),
      Account.builder()
        .id(UUID.randomUUID().toString())
        .name("Mr MOHAMED OUNI")
        .balance(new BigDecimal(66000))
        .iban("C459001390")
        .bban("TN5908032012045900139034")
        .accountOpeningDate(LocalDate.parse("11/08/2016", formatter))
        .build(),
      Account.builder()
        .id(UUID.randomUUID().toString())
        .name("Mme BEN AYED KHANSA")
        .balance(new BigDecimal(82000))
        .iban("7120998891")
        .bban("TN5908003000712099889116")
        .accountOpeningDate(LocalDate.parse("01/02/2021", formatter))
        .build()
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
