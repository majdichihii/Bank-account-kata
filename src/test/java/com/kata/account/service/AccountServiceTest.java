package com.kata.account.service;


import com.kata.account.exception.InsufficientFundsException;
import com.kata.account.model.Account;
import com.kata.account.model.PostBalanceRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.security.auth.login.AccountNotFoundException;
import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

  @InjectMocks
  AccountService accountService;

  @Mock
  EphemeralStorageService ephemeralStorageService;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
  Set<Account> mockAccounts = new HashSet<>(Collections.singletonList(
    new Account()
      .withId("1")
      .withName("VALUE CONSULTING")
      .withBalance(new BigDecimal(1700))
      .withIban("5110980584")
      .withBban("TN5908003000511098058479")
      .withAccountOpeningDate(LocalDate.parse("24/09/2019", formatter))));


  @Test
  public void getAccountsTest() throws JSONException {
    Mockito.when(ephemeralStorageService.getAccounts()).thenReturn(mockAccounts);
    Set<Account> accounts = accountService.getAccounts();

    JSONAssert.assertEquals(accounts.toString(), mockAccounts.toString(), false);
  }

  @Test
  public void balanceDeposit() throws JSONException, AccountNotFoundException {
    PostBalanceRequest postBalanceRequest = new PostBalanceRequest("1", BigDecimal.valueOf(100));
    Mockito.when(ephemeralStorageService.putAccount(Mockito.any())).thenReturn("1");
    Mockito.when(ephemeralStorageService.addTransaction(Mockito.any())).thenReturn(true);
    Mockito.when(ephemeralStorageService.getAccountById(Mockito.any()))
      .thenReturn(mockAccounts.stream().findFirst());
    JSONAssert.assertEquals(accountService.balanceDeposit(postBalanceRequest), "1", false);
  }

  @Test(expected = AccountNotFoundException.class)
  public void balanceDepositAccountDoesNotExist() throws JSONException, AccountNotFoundException {
    PostBalanceRequest postBalanceRequest = new PostBalanceRequest("1", BigDecimal.valueOf(100));
    Mockito.when(ephemeralStorageService.putAccount(Mockito.any())).thenReturn("1");
    Mockito.when(ephemeralStorageService.addTransaction(Mockito.any())).thenReturn(true);
    Mockito.when(ephemeralStorageService.getAccountById(Mockito.any()))
      .thenReturn(Optional.empty());

    JSONAssert.assertEquals(accountService.balanceDeposit(postBalanceRequest), "1", false);
  }

  @Test(expected = InsufficientFundsException.class)
  public void balanceWithdrawalInsufficientFunds() throws JSONException, AccountNotFoundException {
    PostBalanceRequest postBalanceRequest = new PostBalanceRequest("1", BigDecimal.valueOf(100000));
    Mockito.when(ephemeralStorageService.putAccount(Mockito.any())).thenReturn("1");
    Mockito.when(ephemeralStorageService.addTransaction(Mockito.any())).thenReturn(true);
    Mockito.when(ephemeralStorageService.getAccountById(Mockito.any()))
      .thenReturn(mockAccounts.stream().findFirst());
    JSONAssert.assertEquals(accountService.balanceWithdrawal(postBalanceRequest), "1", false);
  }

  @Test
  public void balanceWithdrawal() throws JSONException, AccountNotFoundException {
    PostBalanceRequest postBalanceRequest = new PostBalanceRequest("1", BigDecimal.valueOf(100));
    Mockito.when(ephemeralStorageService.putAccount(Mockito.any())).thenReturn("1");
    Mockito.when(ephemeralStorageService.addTransaction(Mockito.any())).thenReturn(true);
    Mockito.when(ephemeralStorageService.getAccountById(Mockito.any()))
      .thenReturn(mockAccounts.stream().findFirst());
    JSONAssert.assertEquals(accountService.balanceWithdrawal(postBalanceRequest), "1", false);
  }
}
