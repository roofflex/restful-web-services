package com.roofflex.restfulwebservices.service;

import com.roofflex.restfulwebservices.model.Post;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Service to perform CRUD operations to {@link Post} model
 */
public interface PostDaoService {

    @NonNull
    List<Post> getAllPostsForUser(int userId);

    @NonNull
    Post getPostById(int userId, int postId);

    @NonNull
    Post savePost(int userId, @NonNull Post post);
}
