package com.way2dev.springboot.demomvcsecurity.security;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	// adding support for JDBC .. no more hard coded users
	@Bean
	public UserDetailsManager userDetailsManager(DataSource datasource) {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		
		// define query for retrieving user by username for table members
		jdbcUserDetailsManager.setUsersByUsernameQuery(
					"select user_id , pw, active  from members where user_id=?");
		
		// define query for retrieving roles by username from table roles
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select user_id , role from roles where user_id=?");
		return  jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(configurer ->
		configurer
				.requestMatchers("/").hasRole("EMPLOYEE")
				.requestMatchers("/leaders/**").hasRole("MANAGER")
				.requestMatchers("/systems/**").hasRole("ADMIN")
				.anyRequest().authenticated() 
				//means any request should be authenticated
				)
		.formLogin(form ->
		form
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll() //allow everyone to access login page
				)
					.logout(logout -> logout.permitAll()  
				)
					.exceptionHandling(configurer ->
					configurer.accessDeniedPage("/access-denied")
							);
		return http.build();
	}
	
	
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		// creating users john, mary, susan
		UserDetails john = User.builder()
				 .username("john")
				 .password("{noop}test123")
				 .roles("EMPLOYEE")
				 .build(); // creates a userManager instance
				

			 
		UserDetails mary = User.builder()
				 .username("mary")
				 .password("{noop}test123")
				 .roles("EMPLOYEE" , "MANAGER")
				 .build(); // creates a userManager instance


		UserDetails susan= User.builder()
				 .username("susan")
				 .password("{noop}test123")
				 .roles("EMPLOYEE" , "MANAGER" , "ADMIN")
				 .build(); // creates a userManager instance


		return new InMemoryUserDetailsManager(john, mary, susan);
	}
	
	*/
}
