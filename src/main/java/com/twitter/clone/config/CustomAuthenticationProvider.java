package com.twitter.clone.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.User;
import com.twitter.clone.service.UserServiceImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
        
        User userSearch = userServiceImpl.findByEmail(email);


        if(userSearch != null) {
        	//System.out.println("*****************************"+passwordEncoder.encode(password));
        	if(passwordEncoder.matches(password, userSearch.getPassword())) {
        		
        		 return new UsernamePasswordAuthenticationToken(
                 		email, password, new ArrayList<>());
        	}
        }
        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
