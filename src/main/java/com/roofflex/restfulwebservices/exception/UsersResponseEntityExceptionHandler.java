package com.roofflex.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static com.roofflex.restfulwebservices.exception.ErrorDetails.errorDetails;

/**
 * Class that handles exceptions that may happen during requests in controllers
 */
@ControllerAdvice
public class UsersResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleGenericException(@NonNull Exception exception, @NonNull WebRequest request) {
        ErrorDetails errorDetails = errorDetails(LocalDate.now(), exception.getMessage(), request.getDescription(false));

        return ResponseEntity.internalServerError()
                .body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(@NonNull UserNotFoundException exception, @NonNull WebRequest request) {
        ErrorDetails errorDetails = errorDetails(
                LocalDate.now(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        String prefix = MessageFormat.format("Total validation errors: {0}. Errors: ", ex.getFieldErrors().size());

        String validationFailReasons = ex.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", ", prefix, ""));

        ErrorDetails errorDetails = errorDetails(
                LocalDate.now(),
                validationFailReasons,
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
