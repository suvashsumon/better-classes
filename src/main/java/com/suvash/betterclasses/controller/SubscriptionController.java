package com.suvash.betterclasses.controller;

import com.suvash.betterclasses.common.CommonResponse;
import com.suvash.betterclasses.dto.StripeResponse;
import com.suvash.betterclasses.dto.request.CheckoutRequestDto;
import com.suvash.betterclasses.dto.request.CheckoutUpdateRequestDto;
import com.suvash.betterclasses.dto.response.CheckoutResponseDto;
import com.suvash.betterclasses.service.Impl.CheckoutService;
import com.suvash.betterclasses.service.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	private final StripeService stripeService;
	private final CheckoutService checkoutService;

	public SubscriptionController(StripeService stripeService, CheckoutService checkoutService) {
		this.stripeService = stripeService;
		this.checkoutService = checkoutService;
	}

	@PostMapping("/checkout")
	public ResponseEntity<CommonResponse> getPaymentUrl(@RequestBody CheckoutRequestDto checkoutRequestDto) {
		// get stripe session id and payment url
		StripeResponse<Integer, String, String> stripeResponse = stripeService.getSessionUrl(checkoutRequestDto);

		// save the checkout information
		checkoutService.storeCheckoutData(checkoutRequestDto, stripeResponse.getSecond());

		// create a response with status and payment url
		CommonResponse response = CommonResponse.builder()
				.status(stripeResponse.getFirst())
				.data(new CheckoutResponseDto(stripeResponse.getSecond(), stripeResponse.getThird()))
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/checkout/success")
	public ResponseEntity<CommonResponse> checkoutSuccess(
			@RequestBody CheckoutUpdateRequestDto checkoutUpdateRequestDto) {
		// extract the session id
		String sessionId = checkoutUpdateRequestDto.getSessionId();

		// call postHandleSuccessFullCheckout method
		checkoutService.postHandleSuccessFullCheckout(checkoutUpdateRequestDto);
		CommonResponse<Object> response = CommonResponse.builder()
				.status(HttpStatus.OK.value())
				.data("Checkout Successfully Updated")
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/checkout/cancel")
	public String checkoutCancel(@RequestBody CheckoutUpdateRequestDto checkoutUpdateRequestDto) {
		return checkoutUpdateRequestDto.getSessionId();
	}
}
