package com.growfin.ticketingSystem.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.growfin.ticketingSystem.models.Administrator;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private AdministratorService administratorService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Administrator admin = administratorService.findByEmail(email);
		
		return new User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
	}

}
