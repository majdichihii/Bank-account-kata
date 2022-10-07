package com.kata.account.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

  private String id;
  private String name;
  private BigDecimal balance;
  private String iban;
  private String bban;
  private LocalDate accountOpeningDate;

  public Account() {
  }

  public Account(String id, String name, BigDecimal balance, String iban, String bban
    , LocalDate accountOpeningDate) {
    this.id = id;
    this.name = name;
    this.balance = balance;
    this.iban = iban;
    this.bban = bban;
    this.accountOpeningDate = accountOpeningDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account withId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Account withName(String name) {
    this.name = name;
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account withBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Account withIban(String iban) {
    this.iban = iban;
    return this;
  }

  public String getBban() {
    return bban;
  }

  public void setBban(String bban) {
    this.bban = bban;
  }

  public Account withBban(String bban) {
    this.bban = bban;
    return this;
  }

  public LocalDate getAccountOpeningDate() {
    return accountOpeningDate;
  }

  public void setAccountOpeningDate(LocalDate accountOpeningDate) {
    this.accountOpeningDate = accountOpeningDate;
  }

  public Account withAccountOpeningDate(LocalDate accountOpeningDate) {
    this.accountOpeningDate = accountOpeningDate;
    return this;
  }


}
