package com.twitter.clone.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.entity.Tweet;
import com.twitter.clone.service.TweetServiceImpl;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	@Autowired
	private TweetServiceImpl tweetServiceImpl;
	
	@GetMapping("/")
	public List<Tweet> getTweets() {
		List<Tweet> tweets = tweetServiceImpl.findAll();
		Tweet tweet = tweets.get(1);
		
		System.out.println("likes: "+tweet.getLikes().size() +" tweet: "+tweet.getId());
		//System.out.println("===== "+ tweet.getTweet_text());
		return tweets;
		
	}
}
