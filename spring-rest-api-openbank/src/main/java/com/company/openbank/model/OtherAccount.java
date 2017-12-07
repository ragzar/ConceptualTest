package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherAccount {

	private String id;

	private Holder holder;

	@JsonProperty("swift_bic")
	private String swiftBic;

	private Bank bank;

	private String number;

	@JsonProperty("IBAN")
	private String Iban;

	private String kind;

	private Metadata metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Holder getHolder() {
		return holder;
	}

	public void setHolder(Holder holder) {
		this.holder = holder;
	}

	public String getSwiftBic() {
		return swiftBic;
	}

	public void setSwiftBic(String swiftBic) {
		this.swiftBic = swiftBic;
	}

	public String getIban() {
		return Iban;
	}

	public void setIban(String iban) {
		Iban = iban;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return " [id = " + id + ", holder = " + holder + ", swiftBic = " + swiftBic + ", bank = " + bank + ", number = "
				+ number + ", IBAN = " + Iban + ", kind = " + kind + ", metadata = " + metadata + "]";
	}
}