package com.soas.hrm21.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soas.hrm21.entity.AuthenticateBean;

@RestController
@RequestMapping("/api/v1/basicauth")
@CrossOrigin
public class BasicAuthenticationController {

	@GetMapping
	public AuthenticateBean authenticate() {
		return new AuthenticateBean("You are authenticated");
	}
}
