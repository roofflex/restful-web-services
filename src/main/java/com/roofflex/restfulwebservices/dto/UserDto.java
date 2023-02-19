package com.roofflex.restfulwebservices.dto;

import com.roofflex.restfulwebservices.model.User;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * DTO for {@link User} model.
 *
 * @param name      name
 * @param birthdate birthdate
 */
public record UserDto(@Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters length") String name,
                      @Past(message = "Birthdate should be in the past") LocalDate birthdate) {
}
