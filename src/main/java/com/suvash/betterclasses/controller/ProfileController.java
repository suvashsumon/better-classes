package com.suvash.betterclasses.controller;

import com.suvash.betterclasses.common.CommonResponse;
import com.suvash.betterclasses.dto.request.ProfileUpdateRequestDto;
import com.suvash.betterclasses.dto.response.ProfileInfoResponseDto;
import com.suvash.betterclasses.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/info")
    public ResponseEntity<CommonResponse> getProfileInformation()
    {
        ProfileInfoResponseDto profileInfoResponseDto = profileService.getProfileInfo();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder().status(HttpStatus.OK.value()).data(profileInfoResponseDto).build()
        );
    }

    @PostMapping("/info")
    public ResponseEntity<CommonResponse> updateProfileInformation(@Valid @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto)
    {
        profileService.upateProfile(profileUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.builder().status(HttpStatus.OK.value()).data("Profile updated successfully").build()
        );
    }
}
