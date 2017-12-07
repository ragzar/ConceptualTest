package com.company.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.company.configuration.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AppConfigTest {

	@Mock
	private Logger log;

	@Configuration
	@ComponentScan("com.company")
	static class ContextConfiguration {

	}

	@Autowired
	private ApplicationContext context;

	@Test
	public void testAppConfigMonitoringServiceBean() throws Exception {
		AppConfig appConfig = (AppConfig) context.getBean("appConfig");
		assertThat(appConfig.restTemplate(), is(notNullValue()));
	}

}