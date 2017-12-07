package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Holder {

	@JsonProperty("is_alias")
	private Boolean isAlias;

	private String name;

	public Boolean getIsAlias() {
		return isAlias;
	}

	public void setIsAlias(Boolean isAlias) {
		this.isAlias = isAlias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return " [isAlias = " + isAlias + ", name = " + name + "]";
	}
}