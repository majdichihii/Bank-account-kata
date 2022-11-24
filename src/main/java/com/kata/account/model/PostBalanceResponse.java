package com.kata.account.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
public class PostBalanceResponse {

  @JsonProperty("id")
  private String id;

}
