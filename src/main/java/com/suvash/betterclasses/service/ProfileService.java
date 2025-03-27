package com.suvash.betterclasses.service;

import com.suvash.betterclasses.dto.request.ProfileUpdateRequestDto;
import com.suvash.betterclasses.dto.response.ProfileInfoResponseDto;

public interface ProfileService {
    ProfileInfoResponseDto getProfileInfo();
    void upateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);
}
