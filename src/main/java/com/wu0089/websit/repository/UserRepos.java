package com.wu0089.websit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wu0089.websit.entity.User;

public interface UserRepos extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    
}

