package com.company.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception
    {
        auth
            .inMemoryAuthentication().withUser("driver").password("driver")
            .roles("USER").and().withUser("admin").password("admin")
            .roles("USER", "ADMIN");
    }


    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .httpBasic().and().authorizeRequests().antMatchers("/v1/**")
            .hasRole("USER").antMatchers("/v1/**").hasRole("ADMIN").and()
            .csrf().disable().headers().frameOptions().disable();
    }

}
