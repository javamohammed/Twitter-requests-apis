package com.twitter.clone.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tweets")
public class Tweet {

	//`id`, `tweet_text`, `geo_lat`, `geo_long`, `user_id`, `created_at`, `updated_at`
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "tweet_text")
	private String tweet_text;
	
	@Column(name = "geo_lat")
	private String geo_lat;
	
	@Column(name = "geo_long")
	private String geo_long;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updated_at;
	
	@JsonBackReference
	 @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "tweet_id")
    private List<Like> likes = new ArrayList<Like>();
	
	public Tweet() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweet_text() {
		return tweet_text;
	}

	public void setTweet_text(String tweet_text) {
		this.tweet_text = tweet_text;
	}

	public String getGeo_lat() {
		return geo_lat;
	}

	public void setGeo_lat(String geo_lat) {
		this.geo_lat = geo_lat;
	}

	public String getGeo_long() {
		return geo_long;
	}

	public void setGeo_long(String geo_long) {
		this.geo_long = geo_long;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getCreated_at() {
		return timeTostring(created_at);
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return timeTostring(updated_at);
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	private String  timeTostring(Date dt) {

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(dt);
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	
	
}
