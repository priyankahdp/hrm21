package com.soas.hrm21.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9035692900550558818L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
