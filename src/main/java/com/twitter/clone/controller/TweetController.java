package com.twitter.clone.controller;


import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.entity.Like;
import com.twitter.clone.entity.Tweet;
import com.twitter.clone.entity.User;
import com.twitter.clone.service.LikeServiceImpl;
import com.twitter.clone.service.TweetServiceImpl;
import com.twitter.clone.service.UserServiceImpl;

@RestController
@RequestMapping("/api/tweets")
@CrossOrigin
public class TweetController {

	@Autowired
	private TweetServiceImpl tweetServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private LikeServiceImpl likeServiceImpl; 
	
	@GetMapping("/")
	public List<Tweet> getTweets() {
		List<Tweet> tweets = tweetServiceImpl.findAll();
		/*
		for (Tweet tweet : tweets) {
			System.out.println(tweet.getId()+" =>"+tweet.getCreated_at());
			for (Like like : tweet.getLikes()) {
				System.out.println(like.getId());
				
			}
		}
		*/
		return tweets;
		
	}
	@GetMapping("/user/{userId}")
	public List<Tweet> getTweetsByUser(@PathVariable String userId) {
		
		
		try {
			Integer.parseInt(userId);
		} catch (Exception e) {
			return null;
		}
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		List<Tweet> tweets = tweetServiceImpl.findAllByUser(user);
		
		return tweets;
		
	}
	
	@GetMapping("/like/{userId}/{tweetId}")
	public String saveLike(@PathVariable String userId, @PathVariable String tweetId) {
		User user = new User();
		user.setId(Integer.parseInt(userId));
		Tweet tweet = new Tweet();
		tweet.setId(Integer.parseInt(tweetId));
		Like searchedLike = likeServiceImpl.findByUserAndTweet(user, tweet);
		if(searchedLike == null) {
			Like newLike = new Like();
			newLike.setTweet(tweet);
			newLike.setUser(user);
			newLike.setCreated_at(new Date());
			newLike.setUpdated_at(new Date());
			likeServiceImpl.save(newLike);
			return "new";
		}
		likeServiceImpl.delete(searchedLike);
		//System.out.println(searchedLike.getUser().getId() + "---- "+ searchedLike.getTweet().getId());
		return "delete";
	}
}
