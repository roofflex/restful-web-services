package com.roofflex.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

/**
 * Exception thrown in case user is not found by id.
 * Extends RuntimeException so it's unchecked
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException userNotFoundException(int id) {
        return new UserNotFoundException(MessageFormat.format("User with id={0} not found", id));
    }
}
