package com.company.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

	protected final static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		StringBuilder logMessage = new StringBuilder();
		logMessage.append("method: ").append(request.getMethod()).append("\t");
		logMessage.append("uri: ").append(request.getRequestURI()).append("\t");
		logMessage.append("status: ").append(response.getStatus()).append("\t");
		logMessage.append("remoteAddress: ").append(request.getRemoteAddr()).append("\t");

		if (ex != null) {
			LoggingInterceptor.log.error(logMessage.toString(), ex);
		} else {
			LoggingInterceptor.log.info(logMessage.toString());
		}

	}

}
