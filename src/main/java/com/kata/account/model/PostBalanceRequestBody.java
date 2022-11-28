package com.kata.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostBalanceRequestBody {
    private String accountId;
    private BigDecimal amount;

}
