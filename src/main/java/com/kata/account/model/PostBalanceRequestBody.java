package com.kata.account.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostBalanceRequestBody {
  @JsonProperty("accountId")
  private String accountId;
  @JsonProperty("amount")
  private BigDecimal amount;

}
