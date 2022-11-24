package com.kata.account.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PostAccountRequestBody {
    private String name;
    private BigDecimal balance;
    private String iban;
    private String bban;
    private LocalDate accountOpeningDate;
}
