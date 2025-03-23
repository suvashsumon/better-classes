package com.suvash.betterclasses.service;

import com.suvash.betterclasses.entity.User;
import com.suvash.betterclasses.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {
	private final UserRepository userRepository;

	public AuthenticatedUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getAuthenticatedUser() {
		Object principal =
				SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		} else {
			throw new RuntimeException("User is not authenticated");
		}
	}
}
