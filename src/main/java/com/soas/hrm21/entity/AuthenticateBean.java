package com.soas.hrm21.entity;

import lombok.Data;

@Data
public class AuthenticateBean {

	public AuthenticateBean(String message) {
		this.message = message;
	}

	private String message;
}
