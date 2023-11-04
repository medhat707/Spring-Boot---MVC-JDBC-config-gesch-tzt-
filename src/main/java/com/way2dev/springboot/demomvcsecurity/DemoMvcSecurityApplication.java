package com.way2dev.springboot.demomvcsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* ----------PREVIOUS LESSEN ---------------
 * added support for bcrypt algorithm
 * RUN APPLICATION * 
 * /* ----------CURRENT LESSEN ---------------\
 * added new tables instead of users and authorities (default spring configs)
 * now out tables are members and roles
 * STEPS:
 * 1. open security config
 * 2. create variable jdbcUserDetailsManager
 * 3. define queries
 * 		jdbcUserDetailsManager.setUsersByUsernameQuery(
					"select user_id , pw, active  from members where user_id=?");
			where ? is a placeholder processed after entering login username in the form
					
 */
@SpringBootApplication
public class DemoMvcSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMvcSecurityApplication.class, args);
	}

}
