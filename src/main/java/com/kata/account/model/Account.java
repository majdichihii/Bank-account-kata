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
public class Account {

  private String id;
  private String name;
  private BigDecimal balance;
  private String iban;
  private String bban;
  private LocalDate accountOpeningDate;

}
