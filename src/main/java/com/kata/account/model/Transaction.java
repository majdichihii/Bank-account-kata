package com.kata.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;


@Data
@Builder
public class Transaction {

    private String id;
    private String accountId;
    private LocalDate date;
    private BigDecimal transactionAmount;
    private CreditDebitIndicator creditDebitIndicator;

}
