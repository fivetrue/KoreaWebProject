package com.insightkorea.korea.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.insightkorea.korea.Global;
import com.insightkorea.korea.auth.CustomAccessDeniedHandler;
import com.insightkorea.korea.auth.CustomAuthenticationProvider;
import com.insightkorea.korea.auth.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/webjars/**", "/css/**", "/images/**").permitAll()
			.antMatchers("/admin/**").hasAnyRole(Global.Roles.ADMIN)
			.antMatchers("/", "/home").hasAnyRole(Global.Roles.EMPLOYEE, Global.Roles.ADMIN)
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.successHandler(authenticationSuccessHandler)
			.usernameParameter(Global.LoginParams.NAME)
			.passwordParameter(Global.LoginParams.EMPLOYEE_ID)
			.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("configureGlobal");
		auth.authenticationProvider(authenticationProvider);
	}
}
