package com.twitter.clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.clone.entity.Like;
import com.twitter.clone.entity.Tweet;
import com.twitter.clone.entity.User;
import com.twitter.clone.repository.LikeRepository;

@Service
public class LikeServiceImpl {

	@Autowired
	private LikeRepository likeRepository;
	
	public void save(Like like) {
		likeRepository.save(like);
	}
	
	public Like findByUserAndTweet(User user, Tweet tweet) {
		return likeRepository.findByUserAndTweet(user, tweet);
		
	}
	
	public void delete(Like like) {
		 likeRepository.delete(like);
	}
	
}
