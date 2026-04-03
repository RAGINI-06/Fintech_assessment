package com.ragini.financebackend.controller;

import com.ragini.financebackend.entity.User;
import com.ragini.financebackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST → Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // GET → All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}