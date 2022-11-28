package com.kata.account.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditDebitIndicator {
    CRDT("CRDT"), DBIT("DBIT");
    private final String value;

    CreditDebitIndicator(String value) {
        this.value = value;
    }

    @JsonValue
    public String toString() {
        return this.value;
    }


}
