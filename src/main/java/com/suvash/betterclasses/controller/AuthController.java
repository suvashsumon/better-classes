package com.suvash.betterclasses.controller;

import com.suvash.betterclasses.dto.AuthResponseDto;
import com.suvash.betterclasses.dto.LoginDto;
import com.suvash.betterclasses.dto.RegisterDto;
import com.suvash.betterclasses.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto)
    {
        System.out.println(registerDto);
        boolean flag = authService.register(registerDto);
        if(flag)
        {
            LoginDto loginDto = new LoginDto();
            loginDto.setUsername(registerDto.getUsername());
            loginDto.setPassword(registerDto.getPassword());
            String token = authService.login(loginDto);
            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setAccessToken(token);
            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Information", HttpStatus.BAD_REQUEST);
    }
}