package com.company.configuration;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.company.exception.DataNotFoundException;
import com.company.exception.ErrorResource;

@ControllerAdvice
public class ExceptionsAppHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DataNotFoundException.class })
	protected ResponseEntity<ErrorResource> handleInvalidRequest(DataNotFoundException e, WebRequest request) {

		ErrorResource error = new ErrorResource("Invalid Request", e.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus responseStatus = resolveAnnotatedResponseStatus(e);
		return new ResponseEntity<ErrorResource>(error, responseStatus);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	protected ResponseEntity<ErrorResource> handleInvalidRequest(RuntimeException e, WebRequest request) {
		ErrorResource error = new ErrorResource("Invalid Request", e.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus responseStatus = resolveAnnotatedResponseStatus(e);
		return new ResponseEntity<ErrorResource>(error, responseStatus);
	}

	HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
		ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
		if (annotation != null) {
			return annotation.value();
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
