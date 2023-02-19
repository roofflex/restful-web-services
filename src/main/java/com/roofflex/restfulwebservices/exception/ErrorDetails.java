package com.roofflex.restfulwebservices.exception;

import org.springframework.lang.NonNull;

import java.time.LocalDate;

/**
 * Class to aggregate info about the exception
 */
public record ErrorDetails(LocalDate timestamp, String message, String details) {

    public static ErrorDetails errorDetails(@NonNull LocalDate timestamp,
                                            @NonNull String message,
                                            @NonNull String details)
    {
        return new ErrorDetails(timestamp, message, details);
    }
}
