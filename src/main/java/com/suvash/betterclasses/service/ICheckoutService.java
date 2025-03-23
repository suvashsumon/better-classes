package com.suvash.betterclasses.service;

import com.suvash.betterclasses.dto.request.CheckoutRequestDto;

public interface ICheckoutService {
    void storeCheckoutData(CheckoutRequestDto checkoutRequestDto, String stripeSession);
}
