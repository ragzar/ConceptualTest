package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bank {
	private String name;

	@JsonProperty("national_identifier")
	private String nationalIdentifier;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationalIdentifier() {
		return nationalIdentifier;
	}

	public void setNationalIdentifier(String nationalIdentifier) {
		this.nationalIdentifier = nationalIdentifier;
	}

	@Override
	public String toString() {
		return " [name = " + name + ", nationalIdentifier = " + nationalIdentifier + "]";
	}
}
