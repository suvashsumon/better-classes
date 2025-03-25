package com.suvash.betterclasses.repository;

import com.suvash.betterclasses.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    @Query("SELECT c FROM Checkout c WHERE c.stripeSession = :stripeSession")
    Checkout findByStripeSession(@Param("stripeSession") String stripeSession);
}
