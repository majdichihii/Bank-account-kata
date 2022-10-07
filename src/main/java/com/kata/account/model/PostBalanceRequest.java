package com.kata.account.model;

import java.math.BigDecimal;

public class PostBalanceRequest {

  private String accountId;
  private BigDecimal amount;


  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  public PostBalanceRequest(String accountId, BigDecimal amount) {
    this.accountId = accountId;
    this.amount = amount;
  }
}
