package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestEntryPoint restEntryPoint;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("client").password("client").roles("USER").and().withUser("admin")
				.password("admin").roles("USER", "ADMIN");
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers("/v1/**").hasRole("USER").antMatchers("/v1/**")
				.hasRole("ADMIN").and().csrf().disable().headers().cacheControl().and().frameOptions().disable().and()
				.exceptionHandling().authenticationEntryPoint(restEntryPoint);

	}

}
