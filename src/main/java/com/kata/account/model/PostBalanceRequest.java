package com.kata.account.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
public class PostBalanceRequest {
    private String accountId;
    private BigDecimal amount;

}
