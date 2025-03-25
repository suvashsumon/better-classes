package com.suvash.betterclasses.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutResponseDto {
	private String sessionId;
	private String sessionUrl;
}
