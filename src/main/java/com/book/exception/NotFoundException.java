package com.book.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiBaseException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String exception) {
		super(exception);
	}

	@Override
	public HttpStatus getHttpStatusCode() {
		return HttpStatus.NOT_FOUND;
	}
}