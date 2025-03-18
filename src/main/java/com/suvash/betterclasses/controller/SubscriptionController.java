package com.suvash.betterclasses.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
  @PostMapping("/getPaymentUrl")
  public String getPaymentUrl() {
    // Logic to generate payment URL goes here
    return "https://example.com/payment/url";
  }
}
