package com.kata.account.Controller;


import com.kata.account.controller.TransactionController;
import com.kata.account.model.CreditDebitIndicator;
import com.kata.account.model.Transaction;
import com.kata.account.service.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
@WebMvcTest(value = TransactionController.class)
public class TransactionControllerTest {

  private final String OK_STATUS = "200";


  @Autowired
  private MockMvc mockMvc;
  @MockBean
  TransactionService transactionService;

  Set<Transaction> mockTransactions = new HashSet<>(Collections.singletonList(
    Transaction.builder()
      .id("b3cb5122-8d19-4510-b9d9-6591412574c5")
      .accountId("222e568b-1d8c-4b39-99a3-8f820f72483c")
      .date(LocalDate.now())
      .transactionAmount(BigDecimal.valueOf(300))
      .creditDebitIndicator(CreditDebitIndicator.CRDT)
      .build()
  ));

  @Test
  public void getTransactionsTest() throws Exception {
    String accountId ="222e568b-1d8c-4b39-99a3-8f820f72483c";
    Mockito.when(transactionService.getTransactionsByAccountId(accountId)).thenReturn(mockTransactions);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
      "/api/v1/history").queryParam("accountId",accountId).accept(
      MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(result.getResponse().getContentAsString());
    LocalDate expectedDate = LocalDate.now();
    String expected = "[{\"id\":\"b3cb5122-8d19-4510-b9d9-6591412574c5\","
      + "\"accountId\":\"222e568b-1d8c-4b39-99a3-8f820f72483c\","
      + "\"date\":"
      + expectedDate + ","
      + "\"transactionAmount\":300,"
      + "\"creditDebitIndicator\":\"CRDT\"}]";

    JSONAssert.assertEquals(expected, result.getResponse()
      .getContentAsString(), false);
    JSONAssert.assertEquals(OK_STATUS,
      String.valueOf(result.getResponse().getStatus()), false);

  }
}
