package com.claudemirojr.model.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.claudemirojr.exception.NotFoundException;
import com.claudemirojr.model.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService {

	@Autowired
	UserRepository repository;

	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUserName(username);
		if (user != null) {
			return user;
		} else {
			throw new NotFoundException(String.format("Username %s não encontrado", username)); 
		}

	}

}
