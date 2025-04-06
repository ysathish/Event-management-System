package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.model.User;
import com.nt.model.UserPrincipal;
import com.nt.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("404");
		}
		// TODO Auto-generated method stub
		return new UserPrincipal(user);
	}

}
