package com.roofflex.restfulwebservices.service;

import com.roofflex.restfulwebservices.model.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service to perform CRUD operations to User model
 */
public interface UserDaoService {

    @NonNull
    List<User> getAll();

    @NonNull
    User save(@NonNull User user);

    @Nullable
    User getOne(int id);
}
