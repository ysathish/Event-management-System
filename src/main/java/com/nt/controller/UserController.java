package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.User;
import com.nt.service.JwtService;
import com.nt.service.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private AuthenticationManager authenicationManager;
	@Autowired
	private JwtService jwtservice;
	@PostMapping("register")
	public User addUser(@RequestBody User user)
	{
		return service.saveUser(user);
	}
	@PostMapping("login")
	public String login(@RequestBody User user)
	{
		Authentication auth=authenicationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(auth.isAuthenticated())
		{
			return jwtservice.generateToken(user.getUsername());
		}
		else
		return "login failed";
	}

}
