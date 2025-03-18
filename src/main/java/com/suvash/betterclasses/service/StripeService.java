package com.suvash.betterclasses.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.suvash.betterclasses.constant.StripeConstants;
import com.suvash.betterclasses.dto.request.CheckoutRequestDto;
import com.suvash.betterclasses.dto.response.CheckoutResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StripeService {
  @Value("${stripe.secret-key}")
  private String secretKey;

  public CheckoutResponseDto getSessionUrl(CheckoutRequestDto checkoutRequestDto) {
    Stripe.apiKey = secretKey;

    SessionCreateParams.LineItem.PriceData.ProductData productData =
        SessionCreateParams.LineItem.PriceData.ProductData.builder()
            .setName(checkoutRequestDto.getName())
            .build();

    SessionCreateParams.LineItem.PriceData priceData =
        SessionCreateParams.LineItem.PriceData.builder()
            .setCurrency(
                checkoutRequestDto.getCurrency() == null ? "USD" : checkoutRequestDto.getCurrency())
            .setUnitAmount(checkoutRequestDto.getAmount())
            .setProductData(productData)
            .build();

    SessionCreateParams.LineItem lineItem =
        SessionCreateParams.LineItem.builder()
            .setQuantity(checkoutRequestDto.getQuantity())
            .setPriceData(priceData)
            .build();

    SessionCreateParams params =
        SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(checkoutRequestDto.getSuccessUrl())
            .setCancelUrl(checkoutRequestDto.getCancelUrl())
            .addLineItem(lineItem)
            .build();

    Session session = null;
    try {
      session = Session.create(params);
    } catch (StripeException e) {
      e.printStackTrace();
      log.error(StripeConstants.SESSION_CREATION_ERROR);
      return CheckoutResponseDto.builder()
          .status(StripeConstants.ERROR)
          .message(StripeConstants.FAILURE_MESSAGE)
          .sessionUrl(null)
          .sessionId(null)
          .build();
    }

    return CheckoutResponseDto.builder()
        .status(StripeConstants.SUCCESS)
        .message(StripeConstants.SUCCESS_MESSAGE)
        .sessionUrl(session.getUrl())
        .sessionId(session.getId())
        .build();
  }
}
