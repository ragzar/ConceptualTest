package com.company.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Application context setup
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();

		appContext.register(AppConfig.class);
		appContext.register(SwaggerConfig.class);
		appContext.setServletContext(servletContext);
		appContext.setDisplayName("Spring-Rest-Api-OpenBank");

		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
