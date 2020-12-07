package com.twitter.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.clone.entity.Like;
import com.twitter.clone.entity.Tweet;
import com.twitter.clone.entity.User;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	Like findByUserAndTweet(User user, Tweet tweet);

}
