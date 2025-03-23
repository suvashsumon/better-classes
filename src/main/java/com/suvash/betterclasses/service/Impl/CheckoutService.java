package com.suvash.betterclasses.service.Impl;

import com.suvash.betterclasses.dto.request.CheckoutRequestDto;
import com.suvash.betterclasses.entity.Checkout;
import com.suvash.betterclasses.entity.User;
import com.suvash.betterclasses.enums.CheckoutStatus;
import com.suvash.betterclasses.enums.SubscriptionPlan;
import com.suvash.betterclasses.repository.CheckoutRepository;
import com.suvash.betterclasses.repository.UserRepository;
import com.suvash.betterclasses.service.AuthenticatedUserService;
import com.suvash.betterclasses.service.ICheckoutService;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements ICheckoutService {
	private final CheckoutRepository checkoutRepository;
	private final UserRepository userRepository;
	private final AuthenticatedUserService authenticatedUserService;

	public CheckoutService(
			CheckoutRepository checkoutRepository,
			UserRepository userRepository,
			AuthenticatedUserService authenticatedUserService) {
		this.checkoutRepository = checkoutRepository;
		this.userRepository = userRepository;
		this.authenticatedUserService = authenticatedUserService;
	}

	@Override
	public void storeCheckoutData(CheckoutRequestDto checkoutRequestDto, String stripeSession) {

		User user = authenticatedUserService.getAuthenticatedUser();

		Checkout checkout = new Checkout();
		if (checkoutRequestDto.getPlan().equalsIgnoreCase("BASIC"))
			checkout.setSubscriptionPlan(SubscriptionPlan.BASIC);
		else if (checkoutRequestDto.getPlan().equalsIgnoreCase("PREMIUM"))
			checkout.setSubscriptionPlan(SubscriptionPlan.PREMIUM);
		else checkout.setSubscriptionPlan(SubscriptionPlan.TRIAL);
		checkout.setMonth(checkoutRequestDto.getQuantity());
		checkout.setAmount(checkoutRequestDto.getAmount());
		checkout.setCurrency(checkoutRequestDto.getCurrency());
		checkout.setStatus(CheckoutStatus.UNPAID);
		checkout.setUser(user);
		checkout.setStripeSession(stripeSession);
		checkoutRepository.save(checkout);
	}
}
