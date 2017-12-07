package com.company.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.company.exception.DataNotFoundException;
import com.company.openbank.model.OpenBankTransactions;
import com.company.openbank.model.Transactions;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TransactionServiceTest {

	@Mock
	private Environment env;

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	@Spy
	private DefaultTransactionService defaultTransactionService;

	private OpenBankTransactions mockTransaction;

	MockRestServiceServer server;

	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);

		ObjectMapper objectMapper = new ObjectMapper();

		mockTransaction = objectMapper.readValue(this.getClass().getResourceAsStream("/OpenBankTransaction.json"),
				OpenBankTransactions.class);
	}

	@Test
	public void shouldReturnTransactions() throws Exception {

		when(restTemplate.getForObject(anyString(), anyObject(), Matchers.<Object>anyVararg()))
				.thenReturn(mockTransaction);

		List<Transactions> response = defaultTransactionService.list(anyString());

		assertEquals(response.size(), 4);
	}

	@Test(expected = DataNotFoundException.class)
	public void shouldReturnDataNotFound() {
		when(restTemplate.getForObject(anyString(), anyObject(), Matchers.<Object>anyVararg())).thenReturn(null);
		defaultTransactionService.list(anyString());
	}
}
