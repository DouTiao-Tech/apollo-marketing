package com.doutiaotech.apollo.web.controller;

import com.doutiaotech.apollo.infrastructure.mysql.dao.UserDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userDao.findById(id);
    }

}
