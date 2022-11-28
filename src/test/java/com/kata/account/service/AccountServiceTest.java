package com.kata.account.service;


import com.kata.account.exception.AccountNotFoundException;
import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequestBody;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.kata.account.service.impl.AccountServiceImpl;
import com.kata.account.service.impl.EphemeralStorageServiceImpl;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

  @InjectMocks
  AccountServiceImpl accountServiceImpl;

  @Mock
  EphemeralStorageServiceImpl ephemeralStorageServiceImpl;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
  Set<Account> mockAccounts = new HashSet<>(Collections.singletonList(
    Account.builder()
      .id("1")
      .name("VALUE CONSULTING")
      .balance(new BigDecimal(1700))
      .iban("5110980584")
      .bban("TN5908003000511098058479")
      .accountOpeningDate(LocalDate.parse("24/09/2019", formatter))
      .build()
  ));

  @Before
  public void setUp() {
    Mockito.when(ephemeralStorageServiceImpl.getAccounts()).thenReturn(mockAccounts);
    Mockito.when(ephemeralStorageServiceImpl.putAccount(Mockito.any())).thenReturn("1");
    Mockito.when(ephemeralStorageServiceImpl.addTransaction(Mockito.any())).thenReturn(true);
    Mockito.when(ephemeralStorageServiceImpl.getAccountById(Mockito.any()))
      .thenReturn(mockAccounts.stream().findFirst());
  }

  @Test
  public void getAccountsTest(){
    Set<Account> accounts = accountServiceImpl.getAccounts();
    System.out.println("acc:");
    System.out.println(accounts.toString());
    System.out.println("mockAccounts:");
    System.out.println(mockAccounts.toString());
    assertEquals(accounts, mockAccounts);
  }

  @Test
  public void balanceDeposit() throws JSONException, AccountNotFoundException {
    PostBalanceRequestBody postBalanceRequestBody = new PostBalanceRequestBody("1", BigDecimal.valueOf(100));
    JSONAssert.assertEquals(accountServiceImpl.balanceDeposit(postBalanceRequestBody), "1", false);
  }

  @Test(expected = AccountNotFoundException.class)
  public void balanceDepositAccountDoesNotExist() throws JSONException, AccountNotFoundException {
    PostBalanceRequestBody postBalanceRequestBody = new PostBalanceRequestBody("1", BigDecimal.valueOf(100));
    Mockito.when(ephemeralStorageServiceImpl.getAccountById(Mockito.any()))
      .thenReturn(Optional.empty());
    JSONAssert.assertEquals(accountServiceImpl.balanceDeposit(postBalanceRequestBody), "1", false);
  }

  @Test(expected = InsufficientFundsException.class)
  public void balanceWithdrawalInsufficientFunds() throws JSONException, AccountNotFoundException {
    PostBalanceRequestBody postBalanceRequestBody = new PostBalanceRequestBody("1", BigDecimal.valueOf(100000));
    JSONAssert.assertEquals(accountServiceImpl.balanceWithdrawal(postBalanceRequestBody), "1", false);
  }

  @Test
  public void balanceWithdrawal() throws JSONException, AccountNotFoundException {
    PostBalanceRequestBody postBalanceRequestBody = new PostBalanceRequestBody("1", BigDecimal.valueOf(100));
    JSONAssert.assertEquals(accountServiceImpl.balanceWithdrawal(postBalanceRequestBody), "1", false);
  }
}
