package com.suvash.betterclasses.entity;

import com.suvash.betterclasses.enums.SubscriptionPlan;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private SubscriptionPlan subscriptionType;

	@Column(nullable = false)
	private LocalDateTime expirationDate;

	@Column(nullable = false)
	private boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Checkout> checkout;

	@PrePersist
	public void prePersist() {
		this.uuid = UUID.randomUUID().toString();
		this.isActive = true;
		this.expirationDate = LocalDateTime.now().plusDays(30);
		this.subscriptionType = SubscriptionPlan.TRIAL;
	}
}
