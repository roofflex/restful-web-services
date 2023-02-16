package com.roofflex.restfulwebservices.model;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User model
 */
public class User {
    private final int id;
    private final String name;
    private final LocalDate birthDate;

    private static final AtomicInteger totalUsers = new AtomicInteger(0);

    /**
     * Private constructor since id shouldn't be set manually
     *
     * @param id        id
     * @param name      name
     * @param birthDate birth date
     */
    private User(int id, @NonNull String name, @NonNull LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @NonNull
    public static User user(@NonNull String name, @NonNull LocalDate birthDate) {
        return new User(totalUsers.getAndIncrement(), name, birthDate);
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
