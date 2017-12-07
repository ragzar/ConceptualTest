package com.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.datatransferobject.TransactionDTO;
import com.company.exception.DataNotFoundException;
import com.company.mapper.TransactionMapper;
import com.company.service.TransactionService;

@RestController
@RequestMapping("/v1/current-accounts")
public class TransactionsController {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private final TransactionService transactionService;

	@Autowired
	public TransactionsController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping(path = "/{accountId}/transactions")
	public List<TransactionDTO> accountTransactions(@Valid @PathVariable String accountId)
			throws DataNotFoundException {
		log.debug("Rest API - find transactions accountId: " + accountId);

		return TransactionMapper.makeTransactionDTOList(transactionService.list(accountId));
	}

	@GetMapping("/{accountId}/transactions/{transactionType}")
	public List<TransactionDTO> transactionsByType(@Valid @PathVariable String accountId,
			@Valid @PathVariable(required = false) String transactionType) throws DataNotFoundException {

		log.debug("Rest API - Filtering transactions  " + accountId + " -> " + transactionType);

		List<TransactionDTO> listTransactions = accountTransactions(accountId);
		listTransactions = listTransactions.stream()
				.filter(transaction -> transactionType.equals("null") ? transaction.getTransactionType() == null
						: transactionType.equals(transaction.getTransactionType()))
				.collect(Collectors.toList());
		if (listTransactions.isEmpty()) {
			throw new DataNotFoundException("No data found for " + transactionType);
		}
		return listTransactions;
	}

	@GetMapping("/{accountId}/transactions/{transactionType}/totals")
	public Map<String, Double> transactionTotalAmount(@Valid @PathVariable String accountId,
			@Valid @PathVariable String transactionType) throws DataNotFoundException {

		log.debug("Rest API - Currency total amount  " + accountId + " -> " + transactionType);
		Map<String, Double> amount = new HashMap<>();
		List<TransactionDTO> listTransactions = transactionsByType(accountId, transactionType);
		listTransactions.stream().forEach(transaction -> amount.merge(transaction.getTransactionCurrency(),
				transaction.getTransactionAmount(), Double::sum));
		return amount;
	}
}
