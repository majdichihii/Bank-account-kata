package com.kata.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostAccountResponseBody {
    @JsonProperty("id")
    private String id;
}
