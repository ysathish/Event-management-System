package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.nt.model.User;
import com.nt.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository repo;
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	public User saveUser(@RequestBody User user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return  repo.save(user);
	}

}
