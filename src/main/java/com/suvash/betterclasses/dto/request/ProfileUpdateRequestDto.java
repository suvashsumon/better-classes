package com.suvash.betterclasses.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequestDto {
    @NotBlank(message="Name is required")
    private String name;

    @Email(message="email is required")
    private String email;

    @NotBlank(message="Institution is required")
    private String institution;

    @NotBlank(message="About is requred")
    private String about;
}
