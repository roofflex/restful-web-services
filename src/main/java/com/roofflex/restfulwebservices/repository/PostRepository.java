package com.roofflex.restfulwebservices.repository;

import com.roofflex.restfulwebservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to fetch Posts from DB or save to DB
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
