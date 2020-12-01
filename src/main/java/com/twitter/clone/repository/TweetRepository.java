package com.twitter.clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.clone.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
