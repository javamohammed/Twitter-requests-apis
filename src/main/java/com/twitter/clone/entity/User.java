package com.twitter.clone.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "The name should not be empty")
	private String name;
	
	@Column(name = "email")
	@NotEmpty(message = "the email should not be empty")
	@Email(message = "must be a syntactically correct email address")
	private String email;
	
	@Column(name = "email_verified_at")
	private String email_verified_at;
	
	@NotEmpty(message = "The password should not be empty")
	@Size(min = 6, message = "the size must be between 6 and 10")
	@Column(name = "password")
	private String password;
	
	@Column(name = "two_factor_recovery_codes")
	private String two_factor_recovery_codes;
	
	@Column(name = "two_factor_secret")
	private String two_factor_secret;
	
	@Column(name = "remember_token")
	private String remember_token;
	
	@Column(name = "current_team_id")
	private String current_team_id;
	
	@Column(name = "profile_photo_path")
	private String profile_photo_path;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "followers_count")
	private String followers_count;
	
	@Column(name = "friends_count")
	private String friends_count;
	
	@Column(name = "statuses_count")
	private String statuses_count;
	
	@Column(name = "time_zone")
	private String time_zone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updated_at;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_verified_at() {
		return email_verified_at;
	}

	public void setEmail_verified_at(String email_verified_at) {
		this.email_verified_at = email_verified_at;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTwo_factor_recovery_codes() {
		return two_factor_recovery_codes;
	}

	public void setTwo_factor_recovery_codes(String two_factor_recovery_codes) {
		this.two_factor_recovery_codes = two_factor_recovery_codes;
	}

	public String getTwo_factor_secret() {
		return two_factor_secret;
	}

	public void setTwo_factor_secret(String two_factor_secret) {
		this.two_factor_secret = two_factor_secret;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public String getCurrent_team_id() {
		return current_team_id;
	}

	public void setCurrent_team_id(String current_team_id) {
		this.current_team_id = current_team_id;
	}

	public String getProfile_photo_path() {
		return profile_photo_path;
	}

	public void setProfile_photo_path(String profile_photo_path) {
		this.profile_photo_path = profile_photo_path;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(String followers_count) {
		this.followers_count = followers_count;
	}

	public String getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(String friends_count) {
		this.friends_count = friends_count;
	}

	public String getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(String statuses_count) {
		this.statuses_count = statuses_count;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", email_verified_at=" + email_verified_at
				+ ", password=" + password + ", two_factor_recovery_codes=" + two_factor_recovery_codes
				+ ", two_factor_secret=" + two_factor_secret + ", remember_token=" + remember_token
				+ ", current_team_id=" + current_team_id + ", profile_photo_path=" + profile_photo_path + ", location="
				+ location + ", url=" + url + ", description=" + description + ", followers_count=" + followers_count
				+ ", friends_count=" + friends_count + ", statuses_count=" + statuses_count + ", time_zone=" + time_zone
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
 