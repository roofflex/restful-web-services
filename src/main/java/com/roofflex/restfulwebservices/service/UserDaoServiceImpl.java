package com.roofflex.restfulwebservices.service;

import com.roofflex.restfulwebservices.model.User;
import com.roofflex.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.roofflex.restfulwebservices.exception.UserNotFoundException.userNotFoundException;

@Service
public class UserDaoServiceImpl implements UserDaoService {

    @Autowired
    private UserRepository userRepository;

    @NonNull
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @NonNull
    @Override
    public User save(@NonNull User user) {
        return userRepository.save(user);
    }

    @NonNull
    @Override
    public User getById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> userNotFoundException(userId));
    }

    @Override
    public void deleteById(int userId) {
        boolean userExists = userRepository.existsById(userId);

        if (!userExists) {
            throw userNotFoundException(userId);
        }

        userRepository.deleteById(userId);
    }
}
