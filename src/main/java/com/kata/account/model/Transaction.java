package com.kata.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

  private String id;
  private String accountId;
  private LocalDate date;
  private BigDecimal transactionAmount;
  private CreditDebitIndicator creditDebitIndicator;

}
