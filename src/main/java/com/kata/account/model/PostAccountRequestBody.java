package com.kata.account.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAccountRequestBody {
    private String name;
    private BigDecimal balance;
    private String iban;
    private String bban;
    private LocalDate accountOpeningDate;
}
