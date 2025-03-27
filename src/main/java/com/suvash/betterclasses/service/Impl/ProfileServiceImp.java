package com.suvash.betterclasses.service.Impl;

import com.suvash.betterclasses.dto.request.ProfileUpdateRequestDto;
import com.suvash.betterclasses.dto.response.ProfileInfoResponseDto;
import com.suvash.betterclasses.entity.User;
import com.suvash.betterclasses.repository.UserRepository;
import com.suvash.betterclasses.service.AuthenticatedUserService;
import com.suvash.betterclasses.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImp implements ProfileService {
    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ProfileServiceImp(UserRepository userRepository, AuthenticatedUserService authenticatedUserService) {
        this.userRepository = userRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public ProfileInfoResponseDto getProfileInfo()
    {
        User user = authenticatedUserService.getAuthenticatedUser();
        return ProfileInfoResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .institution(user.getInstitution())
                .about(user.getAbout())
                .build();
    }

    public void upateProfile(ProfileUpdateRequestDto profileUpdateRequestDto)
    {
        User user = authenticatedUserService.getAuthenticatedUser();
        user.setName(profileUpdateRequestDto.getName());
        user.setEmail(profileUpdateRequestDto.getEmail());
        user.setInstitution(profileUpdateRequestDto.getInstitution());
        user.setAbout(profileUpdateRequestDto.getAbout());
        userRepository.save(user);
    }
}
