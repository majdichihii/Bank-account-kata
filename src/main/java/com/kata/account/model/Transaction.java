package com.kata.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

  private String id;
  private String accountId;
  private LocalDate date;
  private BigDecimal transactionAmount;
  private CreditDebitIndicator creditDebitIndicator;

  public Transaction() {
  }

  public Transaction(String id, String accountId, LocalDate date,
    BigDecimal transactionAmount, CreditDebitIndicator creditDebitIndicator) {
    this.id = id;
    this.accountId = accountId;
    this.date = date;
    this.transactionAmount = transactionAmount;
    this.creditDebitIndicator = creditDebitIndicator;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Transaction withId(String id) {
    this.id = id;
    return this;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Transaction withAccountId(String accountId) {
    this.accountId = accountId;
    return this;
  }


  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Transaction withDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public Transaction withTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
    return this;
  }

  public CreditDebitIndicator getCreditDebitIndicator() {
    return creditDebitIndicator;
  }

  public void setCreditDebitIndicator(CreditDebitIndicator creditDebitIndicator) {
    this.creditDebitIndicator = creditDebitIndicator;
  }

  public Transaction withCreditDebitIndicator(CreditDebitIndicator creditDebitIndicator) {
    this.creditDebitIndicator = creditDebitIndicator;
    return this;
  }
}
