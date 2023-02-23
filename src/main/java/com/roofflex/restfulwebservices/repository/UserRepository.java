package com.roofflex.restfulwebservices.repository;

import com.roofflex.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to fetch Users from DB or save to DB
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
