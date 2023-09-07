package com.bcbsm.filemgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcbsm.filemgt.model.UserModel;
import com.bcbsm.filemgt.security.BcbsUser;
import com.bcbsm.filemgt.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{username}")
	public UserModel getUser(@PathVariable String username) {
		UserModel user = userService.getUser(username);
		System.out.println(" user :: " + user);
		return user;
	}

	@PostMapping
	public String addUser(@RequestBody UserModel user) {
		return userService.addUser(user);
	}

}
