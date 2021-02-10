package com.embl.zuul.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
        //HTTP Basic authentication
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/person-service/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/person-service").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/person-service/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/person-service/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/person-service/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}


}