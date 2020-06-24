package com.book.exception;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy hh:mm:ss")
    private Date timestamp;
    private String uri;
    private Map<String, String> errors;

    public ValidationError() {
        this.timestamp = new Date();
        this.errors = new HashMap<String, String>();
    }

    public void addError(String error,String message){
        this.errors.put(error,message);
    }

}
