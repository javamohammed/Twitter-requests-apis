package com.twitter.clone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.twitter.clone.entity.Like;
import com.twitter.clone.entity.Tweet;
import com.twitter.clone.entity.User;
import com.twitter.clone.repository.TweetRepository;

@Service
public class TweetServiceImpl {

	@Autowired
	private TweetRepository tweetRepository;
	
	public List<Tweet> findAll(){
		return tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	}
	
	public List<Tweet> findAllByUser(User user){
		return tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
	}
	
	public Tweet findById(int id) {
		return tweetRepository.findById(id).orElse(null);
	}
}
