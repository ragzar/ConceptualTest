package com.company.configuration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.company.exception.DataNotFoundException;

public class ExceptionsAppHandlerTest {

	@Mock
	WebRequest webRequest;

	@Test
	public void testErrorIsHandledAsSuccessfullResponse() {
		assertThat(new ExceptionsAppHandler().handleInvalidRequest(new DataNotFoundException("test"), webRequest)
				.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}