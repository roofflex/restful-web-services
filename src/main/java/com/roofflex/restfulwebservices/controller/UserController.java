package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.dto.UserDto;
import com.roofflex.restfulwebservices.model.User;
import com.roofflex.restfulwebservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.roofflex.restfulwebservices.model.User.user;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.getAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        return userDaoService.getOne(userId);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserDto userDto) {
        return userDaoService.save(user(userDto.name(), userDto.birthdate()));
    }


}
