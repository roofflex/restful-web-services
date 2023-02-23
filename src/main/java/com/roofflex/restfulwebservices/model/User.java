package com.roofflex.restfulwebservices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * User model
 */
@Entity(name = "user")
@Value
@Builder
public class User {
    // @Value will make all class fields private final during compilation
    // (makes use of @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE))

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "birth_date")
    LocalDate birthDate;
}
