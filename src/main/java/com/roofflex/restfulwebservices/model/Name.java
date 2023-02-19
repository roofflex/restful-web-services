package com.roofflex.restfulwebservices.model;

import org.springframework.lang.NonNull;

/**
 * Represents the full name of Person
 */
public record Name(@NonNull String firstName, @NonNull String lastName) {

    public static Name name(@NonNull String firstName, @NonNull String lastName) {
        return new Name(firstName, lastName);
    }
}
