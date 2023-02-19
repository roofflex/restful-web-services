package com.roofflex.restfulwebservices.model;

import org.springframework.lang.NonNull;

/**
 * 1st version of Person model
 */
public record PersonV1(@NonNull String fullName) {

    public static PersonV1 personV1(@NonNull String fullName) {
        return new PersonV1(fullName);
    }
}
