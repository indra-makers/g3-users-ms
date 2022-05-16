package com.co.indra.coinmarketcap.users.controllers;


import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserController userController;


    @PostMapping
    public void create(@Valid @RequestBody User user) {
        userService.createUser(user);
    }
}