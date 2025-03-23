package com.suvash.betterclasses.service;

import com.suvash.betterclasses.dto.LoginDto;
import com.suvash.betterclasses.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
	ResponseEntity<?> login(LoginDto loginDto);

	ResponseEntity<?> register(RegisterDto registerDto);
}
