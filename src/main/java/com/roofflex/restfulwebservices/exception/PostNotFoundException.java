package com.roofflex.restfulwebservices.exception;

import com.roofflex.restfulwebservices.model.Post;
import com.roofflex.restfulwebservices.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

/**
 * Exception thrown if {@link Post} is not found by id for specified {@link User}.
 * Extends RuntimeException so it's unchecked
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

    private PostNotFoundException(String message) {
        super(message);
    }

    @NonNull
    public static PostNotFoundException postNotFoundException(int userId, int postId) {
        return new PostNotFoundException(
                MessageFormat.format(
                        "Post with id={0} not found for user with id={1}",
                        postId,
                        userId
                )
        );
    }
}
