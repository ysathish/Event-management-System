package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);

}
