package com.roofflex.restfulwebservices.model;

import org.springframework.lang.NonNull;

/**
 * 1st version of Person model
 */
public record PersonV2(@NonNull Name name) {

    public static PersonV2 personV2(@NonNull Name name) {
        return new PersonV2(name);
    }
}
