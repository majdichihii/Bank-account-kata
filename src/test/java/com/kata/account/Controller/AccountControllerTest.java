package com.kata.account.Controller;


import com.kata.account.controller.AccountController;
import com.kata.account.mapper.PostAccountRequestBodyAccountMapper;
import com.kata.account.model.Account;
import com.kata.account.service.impl.AccountServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountServiceImpl accountServiceImpl;

  @MockBean
  private PostAccountRequestBodyAccountMapper postAccountRequestBodyAccountMapper;

  private final String OK_STATUS = "200";


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
    Mockito.when(accountServiceImpl.getAccounts()).thenReturn(mockAccounts);
    Mockito.when(accountServiceImpl.balanceDeposit(Mockito.any())).thenReturn("1");
    Mockito.when(accountServiceImpl.balanceWithdrawal(Mockito.any())).thenReturn("1");
  }

  @Test
  public void getAccountsTest() throws Exception {

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
      "/api/v1/getAccounts").accept(
      MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    String expected = "[{\"id\":\"1\""
      + ",\"name\":\"VALUE CONSULTING\""
      + ",\"balance\":1700"
      + ",\"iban\":\"5110980584\""
      + ",\"bban\":\"TN5908003000511098058479\""
      + ",\"accountOpeningDate\":\"2019-09-24\"}]";

    JSONAssert.assertEquals(expected, result.getResponse()
      .getContentAsString(), false);
    JSONAssert.assertEquals(OK_STATUS,
      String.valueOf(result.getResponse().getStatus()), false);

  }

  @Test
  public void balanceDepositTest() throws Exception {
    String examplePostBalanceRequest = "{\"accountId\" : \"1\",\"amount\" : \"100\"}";

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
      "/api/v1/account/deposit").accept(
      MediaType.APPLICATION_JSON).content(examplePostBalanceRequest)
      .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    String expected = "{\"id\":\"1\"}";

    JSONAssert.assertEquals(expected, result.getResponse()
      .getContentAsString(), false);
    JSONAssert.assertEquals(OK_STATUS,
      String.valueOf(result.getResponse().getStatus()), false);

  }

  @Test
  public void balanceWithdrawalTest() throws Exception {
    String examplePostBalanceRequest = "{\"accountId\" : \"1\",\"amount\" : \"100\"}";

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
      "/api/v1/account/withdrawal").accept(
      MediaType.APPLICATION_JSON).content(examplePostBalanceRequest)
      .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    String expected = "{\"id\":\"1\"}";

    JSONAssert.assertEquals(expected, result.getResponse()
      .getContentAsString(), false);
    JSONAssert.assertEquals(OK_STATUS,
      String.valueOf(result.getResponse().getStatus()), false);

  }
}
