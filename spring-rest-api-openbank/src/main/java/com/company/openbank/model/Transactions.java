package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transactions {
	private String id;

	private Details details;

	@JsonProperty("other_account")
	private OtherAccount otherAccount;

	@JsonProperty("this_account")
	private ThisAccount thisAccount;

	private Metadata metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public OtherAccount getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(OtherAccount otherAccount) {
		this.otherAccount = otherAccount;
	}

	public ThisAccount getThisAccount() {
		return thisAccount;
	}

	public void setThisAccount(ThisAccount thisAccount) {
		this.thisAccount = thisAccount;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Transactions [id = " + id + ", details = " + details + ", otherAccount = " + otherAccount
				+ ", thisAccount = " + thisAccount + ", metadata = " + metadata + "]";
	}
}
