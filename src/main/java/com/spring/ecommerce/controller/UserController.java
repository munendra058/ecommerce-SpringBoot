package com.spring.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ecommerce.model.User;

@RestController
public class UserController {

    public User user(@RequestBody User user) {

        return user;
    }

}
