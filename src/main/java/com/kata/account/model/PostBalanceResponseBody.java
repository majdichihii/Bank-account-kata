package com.kata.account.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
public class PostBalanceResponseBody {

  @JsonProperty("id")
  private String id;

}
