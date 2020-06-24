package com.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends ApiBaseException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String exception) {
        super(exception);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
