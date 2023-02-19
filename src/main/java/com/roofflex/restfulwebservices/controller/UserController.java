package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.dto.UserDto;
import com.roofflex.restfulwebservices.model.User;
import com.roofflex.restfulwebservices.service.UserDaoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.roofflex.restfulwebservices.model.User.user;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok()
                .body(userDaoService.getAll());
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "Gets user by Id", description = "Gets user by Id")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        return ResponseEntity.ok()
                .body(userDaoService.getById(userId));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userDaoService.save(user(userDto.name(), userDto.birthdate()));

        // created user's location is current request's path + /id, like .../users/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        // created user's location is returned in "Location" header of response
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userDaoService.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
