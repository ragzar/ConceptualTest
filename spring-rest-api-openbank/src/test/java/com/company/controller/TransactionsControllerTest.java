package com.company.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.company.datatransferobject.TransactionDTO;
import com.company.exception.DataNotFoundException;
import com.company.openbank.model.OpenBankTransactions;
import com.company.openbank.model.Transactions;
import com.company.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TransactionsControllerTest {

	private static final String ANY_ACCOUNT = "any_account";

	private static final String TRANSACTION_TYPE = "cash";

	@InjectMocks
	private TransactionsController transactionsController;

	private List<Transactions> mockTransaction;

	@Mock
	private TransactionService service;

	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);

		ObjectMapper objectMapper = new ObjectMapper();

		mockTransaction = objectMapper
				.readValue(this.getClass().getResourceAsStream("/OpenBankTransaction.json"), OpenBankTransactions.class)
				.getTransactions();
	}

	@Test
	public void testAccountTransactions() throws DataNotFoundException {
		when(service.list(any(String.class))).thenReturn(mockTransaction);

		List<TransactionDTO> response = transactionsController.accountTransactions(ANY_ACCOUNT);
		assertEquals(response.size(), mockTransaction.size());
	}

	@Test
	public void testTransactionsByType() throws DataNotFoundException {
		when(service.list(any(String.class))).thenReturn(mockTransaction);

		List<TransactionDTO> response = transactionsController.transactionsByType(ANY_ACCOUNT, TRANSACTION_TYPE);
		assertEquals(response.size(), 3);

	}
	
	@Test(expected = DataNotFoundException.class) 
	public void testTransactionsByTypeNoResults()  {
		when(service.list(any(String.class))).thenReturn(mockTransaction);

		List<TransactionDTO> response = transactionsController.transactionsByType(ANY_ACCOUNT, "");
		assertEquals(response.size(), 3);

	}

	@Test
	public void testTransactionsTotalAmount() throws DataNotFoundException {
		when(service.list(any(String.class))).thenReturn(mockTransaction);
		Map<String, Double> response = transactionsController.transactionTotalAmount(ANY_ACCOUNT, TRANSACTION_TYPE);
		assertEquals(response.get("EUR"), new Double(-4.35));
	}

}
