package com.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends ApiBaseException {
    private static final long serialVersionUID = 1L;

    public ConflictException(String exception) {
        super(exception);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
