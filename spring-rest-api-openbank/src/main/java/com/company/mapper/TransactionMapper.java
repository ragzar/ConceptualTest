package com.company.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.company.datatransferobject.TransactionDTO;
import com.company.openbank.model.Transactions;

public class TransactionMapper {

	public static TransactionDTO makeTransationDTO(Transactions transaction) {
		TransactionDTO.TransactionDTOBuilder dtoBuilder = TransactionDTO.newBuilder().setId(transaction.getId())
				.setAccountId(transaction.getThisAccount().getId())
				.setCounterpartyAccount(transaction.getOtherAccount().getNumber())
				.setCounterpartyName(transaction.getOtherAccount().getHolder().getName())
				.setCounterPartyLogoPath(transaction.getOtherAccount().getMetadata().getImageUrl())
				.setInstructedAmount(transaction.getDetails().getValue().getAmount())
				.setInstructedCurrency(transaction.getDetails().getValue().getCurrency())
				.setTransactionAmount(transaction.getDetails().getValue().getAmount())
				.setTransactionCurrency(transaction.getDetails().getValue().getCurrency())
				.setTransactionType(transaction.getDetails().getType())
				.setDescription(transaction.getDetails().getDescription());
		return dtoBuilder.createTransactionDTO();
	}

	public static List<TransactionDTO> makeTransactionDTOList(Collection<Transactions> transactions) {
		return transactions.stream().map(TransactionMapper::makeTransationDTO).collect(Collectors.toList());
	}
}
