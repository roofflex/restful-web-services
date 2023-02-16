package com.roofflex.restfulwebservices.model;

import org.springframework.lang.NonNull;

public record HelloWorldBean(String message) {
    @NonNull
    public static HelloWorldBean helloWorldBean(@NonNull String message) {
        return new HelloWorldBean(message);
    }
}
