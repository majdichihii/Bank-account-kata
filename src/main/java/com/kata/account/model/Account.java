package com.kata.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

  @JsonProperty("id")
  private String id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("balance")
  private BigDecimal balance;
  @JsonProperty("iban")
  private String iban;
  @JsonProperty("bban")
  private String bban;
  @JsonProperty("accountOpeningDate")
  private LocalDate accountOpeningDate;

  public Account(String name, BigDecimal balance, String iban, String bban, LocalDate accountOpeningDate) {
    this.name = name;
    this.balance = balance;
    this.iban = iban;
    this.bban = bban;
    this.accountOpeningDate = accountOpeningDate;
  }
}
