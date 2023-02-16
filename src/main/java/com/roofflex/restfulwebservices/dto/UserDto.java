package com.roofflex.restfulwebservices.dto;

import com.roofflex.restfulwebservices.model.User;

import java.time.LocalDate;

/**
 * DTO for {@link User} model.
 *
 * @param name      name
 * @param birthdate birthdate
 */
public record UserDto(String name, LocalDate birthdate) {
}
