package com.doutiaotech.apollo.web.controller;

import com.doutiaotech.apollo.infrastructure.mysql.dao.UserDao;
import com.doutiaotech.apollo.infrastructure.mysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // TODO: user login and logout
    @PostMapping("/api/user")
    public User register(@RequestBody User user) {
        return new User();
    }

    @GetMapping("/api/session")
    public User currentUser(User user) {
        return user;
    }

    @PostMapping("/api/session")
    public User login(@RequestParam String principal,
                      @RequestParam String credential) {
        return new User();
    }

    @DeleteMapping("/api/session")
    public void logout(User user) {
    }
}
