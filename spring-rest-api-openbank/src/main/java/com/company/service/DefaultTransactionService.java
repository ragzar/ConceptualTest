package com.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.company.exception.DataNotFoundException;
import com.company.openbank.model.OpenBankTransactions;
import com.company.openbank.model.Transactions;

@Service
public class DefaultTransactionService implements TransactionService {

	@Value("${openbank.transactions.endpoint}")
	private String url;
	
	@Value("${openbank.transactions.bank}")
	private String bank;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<Transactions> list(String accountId) throws DataNotFoundException {

		List<Transactions> transactions = new ArrayList<>();

		try {
			transactions = restTemplate.getForObject(url, OpenBankTransactions.class,  bank, accountId)
					.getTransactions();
		} catch (Exception e) {
			throw new DataNotFoundException("Could not find data with the request param");
		}
		return transactions;
	}

}
