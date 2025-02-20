package com.suvash.betterclasses.service;

import com.suvash.betterclasses.dto.LoginDto;
import com.suvash.betterclasses.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
}