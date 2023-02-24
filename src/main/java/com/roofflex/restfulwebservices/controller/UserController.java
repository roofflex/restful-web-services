package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.dto.PostDto;
import com.roofflex.restfulwebservices.dto.UserDto;
import com.roofflex.restfulwebservices.model.Post;
import com.roofflex.restfulwebservices.model.User;
import com.roofflex.restfulwebservices.service.PostDaoService;
import com.roofflex.restfulwebservices.service.UserDaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private PostDaoService postDaoService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok()
                .body(userDaoService.getAll());
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "Gets user by Id", description = "Gets user by Id")
    public ResponseEntity<EntityModel<User>> getUser(@PathVariable int userId) {
        EntityModel<User> userModel = EntityModel.of(userDaoService.getById(userId));

        Link link = linkTo(methodOn(this.getClass()).getAllUsers()).withRel("all-users");
        userModel.add(link);

        return ResponseEntity.ok()
                .body(userModel);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        User userToSave = User.builder()
                .name(userDto.name())
                .birthDate(userDto.birthdate())
                .build();

        User createdUser = userDaoService.save(userToSave);

        // created user's location is current request's path + /id, like .../users/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        // created user's location is returned in "Location" header of response
        return ResponseEntity.created(location)
                .body(createdUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userDaoService.deleteById(userId);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<CollectionModel<Post>> getAllPostsForUser(@PathVariable int userId) {
        List<Post> posts = postDaoService.getAllPostsForUser(userId);

        CollectionModel<Post> postsEntityModel = CollectionModel.of(posts);
        Link link = linkTo(methodOn(this.getClass()).getUser(userId)).withRel("user");
        postsEntityModel.add(link);

        return ResponseEntity.ok()
                .body(postsEntityModel);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<EntityModel<Post>> getPostById(@PathVariable int userId, @PathVariable int postId) {
        Post post = postDaoService.getPostById(userId, postId);

        EntityModel<Post> postEntityModel = EntityModel.of(post);
        Link link = linkTo(methodOn(this.getClass()).getUser(userId)).withRel("user");
        postEntityModel.add(link);

        return ResponseEntity.ok()
                .body(postEntityModel);
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<EntityModel<Post>> createPost(@PathVariable int userId, @Valid @RequestBody PostDto postDto) {
        Post postToCreate = Post.builder()
                .description(postDto.description())
                .build();

        Post createdPost = postDaoService.savePost(userId, postToCreate);

        EntityModel<Post> postEntityModel = EntityModel.of(createdPost);
        Link link = linkTo(methodOn(this.getClass()).getUser(userId)).withRel("user");
        postEntityModel.add(link);

        // created post's location is current request's path + /id, like .../users/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(postEntityModel);
    }
}
