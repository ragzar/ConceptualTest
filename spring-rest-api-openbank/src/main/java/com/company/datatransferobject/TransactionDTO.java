package com.company.datatransferobject;

public class TransactionDTO {

	private String id;

	private String accountId;

	private String counterpartyAccount;

	private String counterpartyName;

	private String counterPartyLogoPath;

	private double instructedAmount;

	private String instructedCurrency;

	private double transactionAmount;

	private String transactionCurrency;

	private String transactionType;

	private String description;

	public TransactionDTO() {
	}

	public TransactionDTO(String id, String accountId, String counterpartyAccount, String counterpartyName,
			String counterPartyLogoPath, double instructedAmount, String instructedCurrency, double transactionAmount,
			String transactionCurrency, String transactionType, String description) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.counterpartyAccount = counterpartyAccount;
		this.counterpartyName = counterpartyName;
		this.counterPartyLogoPath = counterPartyLogoPath;
		this.instructedAmount = instructedAmount;
		this.instructedCurrency = instructedCurrency;
		this.transactionAmount = transactionAmount;
		this.transactionCurrency = transactionCurrency;
		this.transactionType = transactionType;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public String getCounterPartyLogoPath() {
		return counterPartyLogoPath;
	}

	public double getInstructedAmount() {
		return instructedAmount;
	}

	public String getInstructedCurrency() {
		return instructedCurrency;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getDescription() {
		return description;
	}

	public static TransactionDTOBuilder newBuilder() {
		return new TransactionDTOBuilder();
	}

	public static class TransactionDTOBuilder {
		private String id;

		private String accountId;

		private String counterpartyAccount;

		private String counterpartyName;

		private String counterPartyLogoPath;

		private double instructedAmount;

		private String instructedCurrency;

		private double transactionAmount;

		private String transactionCurrency;

		private String transactionType;

		private String description;

		public TransactionDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public TransactionDTOBuilder setAccountId(String accountId) {
			this.accountId = accountId;
			return this;
		}

		public TransactionDTOBuilder setCounterpartyAccount(String counterpartyAccount) {
			this.counterpartyAccount = counterpartyAccount;
			return this;
		}

		public TransactionDTOBuilder setCounterpartyName(String counterpartyName) {
			this.counterpartyName = counterpartyName;
			return this;
		}

		public TransactionDTOBuilder setCounterPartyLogoPath(String counterPartyLogoPath) {
			this.counterPartyLogoPath = counterPartyLogoPath;
			return this;
		}

		public TransactionDTOBuilder setInstructedAmount(double instructedAmount) {
			this.instructedAmount = instructedAmount;
			return this;
		}

		public TransactionDTOBuilder setInstructedCurrency(String instructedCurrency) {
			this.instructedCurrency = instructedCurrency;
			return this;
		}

		public TransactionDTOBuilder setTransactionAmount(double transactionAmount) {
			this.transactionAmount = transactionAmount;
			return this;
		}

		public TransactionDTOBuilder setTransactionCurrency(String transactionCurrency) {
			this.transactionCurrency = transactionCurrency;
			return this;
		}

		public TransactionDTOBuilder setTransactionType(String transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public TransactionDTOBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public TransactionDTO createTransactionDTO() {
			return new TransactionDTO(id, accountId, counterpartyAccount, counterpartyName, counterPartyLogoPath,
					instructedAmount, instructedCurrency, transactionAmount, transactionCurrency, transactionType,
					description);
		}

	}
}
