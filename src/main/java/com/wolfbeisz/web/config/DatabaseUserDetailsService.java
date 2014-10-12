package com.wolfbeisz.web.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wolfbeisz.model.User;
import com.wolfbeisz.repository.UserRepository;

public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo_;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userRepo_.findByEmail(username);
		if (user == null)
		{
			throw new UsernameNotFoundException("'"+username+"' not found");
		}
		
		return new  org.springframework.security.core.userdetails.User(username,"none",Arrays.asList(new SimpleGrantedAuthority("USER")));
	}	
}
