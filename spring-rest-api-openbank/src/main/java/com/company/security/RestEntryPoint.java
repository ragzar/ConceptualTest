package com.company.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("restEntryPoint")
public class RestEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream()
				.println("{\"code\": \"Unathorized\",  \"message\": \"" + authException.getMessage() + "\"  }");
	}
}