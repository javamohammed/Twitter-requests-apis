package com.twitter.clone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.clone.entity.Tweet;
import com.twitter.clone.repository.TweetRepository;

@Service
public class TweetServiceImpl {

	@Autowired
	private TweetRepository tweetRepository;
	
	public List<Tweet> findAll(){
		return tweetRepository.findAll();
	}
}
