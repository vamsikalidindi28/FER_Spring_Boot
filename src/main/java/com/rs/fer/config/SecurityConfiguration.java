package com.rs.fer.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	 protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/public/**").permitAll() // Publicly accessible URLs
	                .anyRequest().authenticated() // All other URLs require authentication
	                .and()
	            .formLogin()
	                .loginPage("/login") // Custom login page
	                .permitAll()
	                .and() 
	            .logout() 
	                .permitAll();
	    }
}	
