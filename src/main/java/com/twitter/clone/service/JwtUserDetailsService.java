package com.twitter.clone.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.twitter.clone.entity.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserServiceImpl userServiceImpl; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User userSearch = userServiceImpl.findByEmail(username);
		//System.out.println(userSearch);
		if (userSearch != null) 
		{
			UserDetails user = new UserDetails() {
				
				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isCredentialsNonExpired() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isAccountNonLocked() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isAccountNonExpired() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public String getUsername() {
					// TODO Auto-generated method stub
					return username;
				}
				
				@Override
				public String getPassword() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			;
			System.out.println(user.getPassword()+ "++"+user.getUsername());
			return user;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}