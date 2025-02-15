package com.suvash.betterclasses.Service;

import com.suvash.betterclasses.Dto.LoginDto;
import com.suvash.betterclasses.Dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
}