package com.suvash.betterclasses.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDto {
	private Long amount;
	private String currency;
	private Long quantity;
	private String plan;
	private String successUrl;
	private String cancelUrl;
}
