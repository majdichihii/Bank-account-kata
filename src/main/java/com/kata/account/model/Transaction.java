package com.kata.account.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
public class Transaction {

    private String id;
    private String accountId;
    private LocalDate date;
    private BigDecimal transactionAmount;
    private CreditDebitIndicator creditDebitIndicator;

}
