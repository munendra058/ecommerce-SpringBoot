package com.spring.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ecommerce.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
