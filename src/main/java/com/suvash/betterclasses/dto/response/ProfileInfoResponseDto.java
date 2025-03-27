package com.suvash.betterclasses.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileInfoResponseDto {
    private String name;
    private String email;
    private String institution;
    private String about;
}
