package com.twitter.clone.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.clone.config.CustomAuthenticationProvider;
import com.twitter.clone.config.JwtTokenUtil;
import com.twitter.clone.entity.User;
import com.twitter.clone.model.JwtRequest;
import com.twitter.clone.model.JwtResponse;
import com.twitter.clone.service.JwtUserDetailsService;
import com.twitter.clone.service.UserServiceImpl;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private CustomAuthenticationProvider authenticationManager;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception 
	{
		String username = authenticationRequest.getUsername();
		if(authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword()) == null) {
			username = "";
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
		 // final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		//JwtUserDetails userDetails = new JwtUserDetails();
		//userDetails.setUsername(authenticationRequest.getUsername());
			
			
		final String token = jwtTokenUtil.generateToken(userDetails);
		User userSearch = userServiceImpl.findByEmail(username);
		userSearch.setPassword("");
		return ResponseEntity.ok(new JwtResponse(token, userSearch));
	}
	
	
	@PostMapping("/api/signin")
	public  ResponseEntity<?> signinAndCreateAuthenticationToken(@RequestBody User user) throws Exception{
		
		User userSearch = userServiceImpl.findByEmail(user.getEmail());
		
		if(userSearch != null) {
			return new ResponseEntity<>(new JwtResponse("This Email already exists !!",null), HttpStatus.BAD_REQUEST); 
		}
		if(user.getName() == "" || user.getName() == null) {
			return new ResponseEntity<>(new JwtResponse("Please enter your name !!",null), HttpStatus.BAD_REQUEST); 
		}
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userServiceImpl.save(user);
		//TimeUnit.SECONDS.sleep(5);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail()); 
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token, null)); 
	}
	
	private Authentication authenticate(String username, String password) throws Exception {
		try {
		return	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			//System.out.println(authenticationManager.toString()+" &&&&&&&&&&&&&");
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}