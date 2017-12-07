package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find data with the criteria")
public class DataNotFoundException extends RuntimeException {
	static final long serialVersionUID = -3387516993334229948L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
