package com.kata.account.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
}
