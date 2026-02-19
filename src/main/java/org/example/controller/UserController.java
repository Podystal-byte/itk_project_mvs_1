package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.findAllUsers();
    }
}