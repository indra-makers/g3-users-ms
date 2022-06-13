package com.co.indra.coinmarketcap.users.controllers;

import com.co.indra.coinmarketcap.users.config.Routes;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.USERS_PATH)
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public void create(@RequestBody User user) {
		userService.createUser(user);
	}

	@GetMapping(Routes.USER_PATH_PARAM)
	public User getUser(@PathVariable("idUser") int idUser) {
		return userService.getUsers(idUser);
	}

}
