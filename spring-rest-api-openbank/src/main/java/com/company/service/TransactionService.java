package com.company.service;

import java.util.List;

import com.company.exception.DataNotFoundException;
import com.company.openbank.model.Transactions;

public interface TransactionService {

	List<Transactions> list(String accountId) throws DataNotFoundException;

}
