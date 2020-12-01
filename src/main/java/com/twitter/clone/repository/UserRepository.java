package com.twitter.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.clone.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
