package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThisAccount {

	private String id;

	@JsonProperty("holders")
	private Holder[] holders;

	@JsonProperty("swift_bic")
	private String swiftBic;

	private Bank bank;

	private String number;

	@JsonProperty("IBAN")
	private String Iban;

	private String kind;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Holder[] getHolders() {
		return holders;
	}

	public void setHolders(Holder[] holders) {
		this.holders = holders;
	}

	public String getSwiftBic() {
		return swiftBic;
	}

	public void setSwiftBic(String swiftBic) {
		this.swiftBic = swiftBic;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIban() {
		return Iban;
	}

	public void setIban(String iban) {
		Iban = iban;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return " [id = " + id + ", holders = " + holders + ", swiftBic = " + swiftBic + ", bank = " + bank
				+ ", number = " + number + ", IBAN = " + Iban + ", kind = " + kind + "]";
	}
}
