package com.suvash.betterclasses.service;

import com.suvash.betterclasses.dto.request.CheckoutRequestDto;
import com.suvash.betterclasses.dto.request.CheckoutUpdateRequestDto;

public interface ICheckoutService {
	void storeCheckoutData(CheckoutRequestDto checkoutRequestDto, String stripeSession);

	boolean postHandleSuccessFullCheckout(CheckoutUpdateRequestDto checkoutUpdateRequestDto);
}
