package com.way2dev.springboot.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	@GetMapping("/showMyLoginPage")
	public String showLogin() {
		
		return "fancy-login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		
		return "access-denied";
	}

}
