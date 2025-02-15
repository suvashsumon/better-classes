package com.suvash.betterclasses.Service.Impl;

import com.suvash.betterclasses.Config.JwtTokenProvider;
import com.suvash.betterclasses.Dto.LoginDto;
import com.suvash.betterclasses.Dto.RegisterDto;
import com.suvash.betterclasses.Model.Role;
import com.suvash.betterclasses.Model.User;
import com.suvash.betterclasses.Repository.RoleRepository;
import com.suvash.betterclasses.Repository.UserRepository;
import com.suvash.betterclasses.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(LoginDto loginDto) {

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

        // 04 - Return the token to controller
        return token;
    }

    @Override
    public boolean register(RegisterDto registerDto)
    {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return false;
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
//            throw new ResourceAlreadyExistsException("Email is already registered!");
            return false;
        }

        // create role for user
        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        // Create a new user object
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(roles);  // If roles need to be assigned

        // Save the user to the database
        userRepository.save(user);
        return true;
    }
}