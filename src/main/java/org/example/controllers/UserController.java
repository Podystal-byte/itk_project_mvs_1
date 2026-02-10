package org.example.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.exceptions.NotFoundException;
import org.example.model.Order;
import org.example.model.User;
import org.example.model.Views;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    @JsonView(Views.Internal.class)
    public User getUserById(@PathVariable("user_id") Long user_id) throws NotFoundException {
        return userService.getUserWithDetails(user_id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("{user_id}")
    public User addOrder(@PathVariable("user_id") Long userId, @RequestBody Order order) throws NotFoundException {
        return userService.addOrder(userId, order);
    }
}
