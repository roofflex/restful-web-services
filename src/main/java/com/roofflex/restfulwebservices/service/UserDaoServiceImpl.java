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
    UserRepository userRepository;

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
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> userNotFoundException(id));
    }

    @Override
    public void deleteById(int id) {
        boolean userExists = userRepository.existsById(id);

        if (!userExists) {
            throw userNotFoundException(id);
        }

        userRepository.deleteById(id);
    }
}
