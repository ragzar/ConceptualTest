package com.company.packer.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

public class Package {

    @NotNull(message="Package weight not processed")
    @DecimalMax("100.00")
    private Double weight;

    @Valid
    @NotEmpty(message="No items processed")
    @UniqueElements
    private List<Item> items;

    public Package(Double weight, List<Item> items) {
	super();
	this.weight = weight;
	this.items = items;
    }
    
    public Package() {
    }

    public Double getWeight() {
	return weight;
    }

    public void setWeight(Double weight) {
	this.weight = weight;
    }

    public List<Item> getItems() {
	return items;
    }

    public void setItems(List<Item> items) {
	this.items = items;
    }

    @Override
    public String toString() {
	return "Package [weightLimit=" + weight + ", items=" + items + "]";
    }
}
