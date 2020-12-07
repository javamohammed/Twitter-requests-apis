package com.twitter.clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.clone.entity.User;
import com.twitter.clone.repository.UserRepository;


@Service
public class UserServiceImpl  {

	@Autowired
	private UserRepository userRepository;
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}
}
