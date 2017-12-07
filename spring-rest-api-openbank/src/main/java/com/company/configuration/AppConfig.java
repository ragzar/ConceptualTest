package com.company.configuration;

import javax.annotation.PostConstruct;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.util.LoggingInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.company" })
@PropertySources({ @PropertySource(value = "classpath:spring-rest-api-openbank.properties"),
				   @PropertySource(value = "file:${catalina.base}/conf/Catalina/localhost/spring-rest-api-openbank.properties", ignoreResourceNotFound = true) })
public class AppConfig implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
	private static final String CLASSPATH_LOG = "spring.rest.api.openbank.log4j.file";

	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@PostConstruct
	public void postInitSetup() {
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource(env.getProperty(CLASSPATH_LOG)));
		log.info("Spring Rest API Service Initialized");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
