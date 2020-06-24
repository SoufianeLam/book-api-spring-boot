package com.book.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiBaseException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatusCode();
}

