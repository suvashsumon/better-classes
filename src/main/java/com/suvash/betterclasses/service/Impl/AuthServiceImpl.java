package com.suvash.betterclasses.service.Impl;

import com.suvash.betterclasses.common.CommonErrorResponse;
import com.suvash.betterclasses.common.CommonSuccessResponse;
import com.suvash.betterclasses.config.JwtTokenProvider;
import com.suvash.betterclasses.dto.AuthResponseDto;
import com.suvash.betterclasses.dto.LoginDto;
import com.suvash.betterclasses.dto.RegisterDto;
import com.suvash.betterclasses.dto.response.UnauthorizedResponseDto;
import com.suvash.betterclasses.dto.response.UserAlreadyExistResponseDto;
import com.suvash.betterclasses.model.Role;
import com.suvash.betterclasses.model.User;
import com.suvash.betterclasses.repository.RoleRepository;
import com.suvash.betterclasses.repository.UserRepository;
import com.suvash.betterclasses.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {

        try
        {
            // 01 - AuthenticationManager is used to authenticate the user
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 03 - Generate the token based on username and secret key
            String token = jwtTokenProvider.generateToken(authentication);

            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setAccessToken(token);

            // 04 - Return the token to controller
            return new ResponseEntity<>(
                    new CommonSuccessResponse<>(200, authResponseDto),
                    HttpStatus.OK
            );
        } catch (Exception e)
        {
            return new ResponseEntity<>(
                    new CommonSuccessResponse<>(401, new UnauthorizedResponseDto("Invalid username or password!")),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            UserAlreadyExistResponseDto userAlreadyExistDto = new UserAlreadyExistResponseDto("Username already exist.");
            return new ResponseEntity<>(
                    new CommonErrorResponse<>(409, userAlreadyExistDto),
                    HttpStatus.CONFLICT
            );
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            UserAlreadyExistResponseDto userAlreadyExistDto = new UserAlreadyExistResponseDto("Email already exist.");
            return new ResponseEntity<>(
                    new CommonErrorResponse<>(409, userAlreadyExistDto),
                    HttpStatus.CONFLICT
            );
        }

        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(registerDto.getUsername());
        loginDto.setPassword(registerDto.getPassword());
        return this.login(loginDto);
    }
}