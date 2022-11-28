package com.kata.account.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private String id;
    private String name;
    private BigDecimal balance;
    private String iban;
    private String bban;
    private LocalDate accountOpeningDate;

    public Account(String name, BigDecimal balance, String iban, String bban, LocalDate accountOpeningDate) {
        this.name = name;
        this.balance = balance;
        this.iban = iban;
        this.bban = bban;
        this.accountOpeningDate = accountOpeningDate;
    }
}
