package com.suvash.betterclasses.controller;

import com.suvash.betterclasses.common.CommonResponse;
import com.suvash.betterclasses.dto.request.CheckoutRequestDto;
import com.suvash.betterclasses.dto.response.CheckoutResponseDto;
import com.suvash.betterclasses.service.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
  private StripeService stripeService;

  public SubscriptionController(StripeService stripeService) {
    this.stripeService = stripeService;
  }

  @PostMapping("/checkout")
  public ResponseEntity<CommonResponse> getPaymentUrl(
      @RequestBody CheckoutRequestDto checkoutRequestDto) {
    CommonResponse response = stripeService.getSessionUrl(checkoutRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
