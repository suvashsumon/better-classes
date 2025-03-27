package com.suvash.betterclasses.controller;

import com.suvash.betterclasses.dto.response.ProfileInfoResponseDto;
import com.suvash.betterclasses.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/info")
    public ResponseEntity<ProfileInfoResponseDto> getProfileInformation()
    {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getPrifileInfo());
    }
}
