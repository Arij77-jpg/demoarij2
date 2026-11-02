package com.hellohorizon.demoarij2.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 * PromotionService - Chapter 3 Activity 1
 *
 * Design Decision & Single Responsibility Principle (SRP) Justification:
 * ----------------------------------------------------------------------
 * Promotional and discount calculations belong in this dedicated @Stateless service
 * rather than as a method on CartService.
 *
 * 1. Single Responsibility Principle:
 *    CartService is responsible solely for holding and manipulating user cart state
 *    (items added, removed, cleared). It should not be burdened with pricing policies,
 *    coupon rules, seasonal percentage reductions, or tax calculations.
 *
 * 2. Separation of Concerns & Reusability:
 *    Pricing rules and marketing promotions change frequently and independently of cart storage.
 *    By extracting PromotionService into its own stateless bean, promotion rules can easily be
 *    injected, mocked, swapped, or reused across checkout, billing, and quotes without polluting
 *    the session state of CartService.
 */
@Stateless
public class PromotionService {

    public static final double DISCOUNT_RATE = 0.10; // Flat 10% discount

    @Inject
    private CartService cartService;

    /**
     * Calculates the monetary discount amount based on the current cart total.
     */
    public double calculateDiscountAmount() {
        if (cartService == null) {
            return 0.0;
        }
        return cartService.getTotal() * DISCOUNT_RATE;
    }

    /**
     * Calculates the final discounted cart total (total minus 10% discount).
     */
    public double calculateDiscountedTotal() {
        if (cartService == null) {
            return 0.0;
        }
        double total = cartService.getTotal();
        return total * (1.0 - DISCOUNT_RATE);
    }

    /**
     * Helper to apply discount to any arbitrary subtotal amount.
     */
    public double applyDiscount(double subtotal) {
        return subtotal * (1.0 - DISCOUNT_RATE);
    }
}
