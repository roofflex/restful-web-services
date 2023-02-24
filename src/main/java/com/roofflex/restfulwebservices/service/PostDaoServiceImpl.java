package com.roofflex.restfulwebservices.service;

import com.roofflex.restfulwebservices.model.Post;
import com.roofflex.restfulwebservices.model.User;
import com.roofflex.restfulwebservices.repository.PostRepository;
import com.roofflex.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.roofflex.restfulwebservices.exception.PostNotFoundException.postNotFoundException;
import static com.roofflex.restfulwebservices.exception.UserNotFoundException.userNotFoundException;

@Service
public class PostDaoServiceImpl implements PostDaoService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @NonNull
    @Override
    public List<Post> getAllPostsForUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFoundException(userId));

        return user.getPosts();
    }

    @NonNull
    @Override
    public Post getPostById(int userId, int postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFoundException(userId));

        return user.getPosts().stream()
                .filter(post -> postId == post.getId())
                .findFirst()
                .orElseThrow(() -> postNotFoundException(userId, postId));
    }

    @NonNull
    @Override
    public Post savePost(int userId, @NonNull Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFoundException(userId));

        post.setUser(user);
        return postRepository.save(post);
    }
}
