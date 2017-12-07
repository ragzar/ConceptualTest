package com.company.exception;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import com.company.packer.model.Package;

public class APIException extends Exception {

    private static final long serialVersionUID = 5199629890017767754L;

    public APIException(String message) {
	super(message);
    }

    public APIException(Set<ConstraintViolation<Package>> validations) {
	super(validations.stream().map(v -> {
	    String message = "";
	    message = "Line with value " + v.getRootBean().getWeight() + " has invalid value " + v.getInvalidValue()
		    + " " + v.getMessage();
	    return message;
	}).collect(Collectors.joining("\n")));
    }
}
