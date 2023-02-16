package com.roofflex.restfulwebservices.service;

import com.roofflex.restfulwebservices.model.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.roofflex.restfulwebservices.model.User.user;

@Service
public class UserDaoServiceImpl implements UserDaoService {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(user("Jack", LocalDate.of(1995, 4, 13)));
        users.add(user("John", LocalDate.of(1997, 3, 9)));
        users.add(user("Jerry", LocalDate.of(2001, 1, 28)));
    }

    @NonNull
    @Override
    public List<User> getAll() {
        return users;
    }

    @NonNull
    @Override
    public User save(@NonNull User user) {
        users.add(user);
        return user;
    }

    @Nullable
    @Override
    public User getOne(int id) {
        return users.stream()
                .filter(user -> id == user.getId())
                .findFirst()
                .orElse(null);
    }
}
