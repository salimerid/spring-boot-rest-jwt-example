package com.spring.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class DuplicateUserNameException extends RuntimeException{
    private String message;

    public DuplicateUserNameException( String message) {
        super(String.format("%s ", message));
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
