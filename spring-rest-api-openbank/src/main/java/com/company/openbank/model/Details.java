package com.company.openbank.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {

	@JsonProperty("new_balance")
	private Value newBalance;

	private String description;

	private Value value;

	private String type;

	private Date posted;

	private Date completed;

	public Value getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(Value newBalance) {
		this.newBalance = newBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public Date getCompleted() {
		return completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return " [newBalance = " + newBalance + ", description = " + description + ", value = " + value + ", type = "
				+ type + ", posted = " + posted + ", completed = " + completed + "]";
	}
}
