package com.suvash.betterclasses.entity;

import com.suvash.betterclasses.enums.CheckoutStatus;
import com.suvash.betterclasses.enums.SubscriptionPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SubscriptionPlan subscriptionPlan;

	@Column(nullable = false)
	private Long month;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private String currency;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CheckoutStatus status;

	@Column(nullable = false)
	private String stripeSession;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
