package com.company.packer.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

public class Item {

    @NotNull
    private Long id;

    @NotNull
    @DecimalMax("100.00")
    private Double weight;

    @NotNull
    @DecimalMax("100.00")
    private Double cost;

    public Item(Long id, Double weight, Double cost) {
	super();
	this.id = id;
	this.weight = weight;
	this.cost = cost;
    }

    public Item() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Double getWeight() {
	return weight;
    }

    public void setWeight(Double weight) {
	this.weight = weight;
    }

    public Double getCost() {
	return cost;
    }

    public void setCost(Double cost) {
	this.cost = cost;
    }

    @Override
    public String toString() {
	return "Item [id=" + id + ", weight=" + weight + ", cost=" + cost + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Item)) {
	    return false;
	}
	Item other = (Item) obj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	return true;
    }

}
