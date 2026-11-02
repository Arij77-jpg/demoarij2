package com.hellohorizon.demoarij2.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * OrderService - Lab 3 Step 6 & Chapter 3 Activity 3
 *
 * Stateless session bean defining the transactional boundary for order creation.
 * Includes guard validation against empty carts: throws an IllegalStateException
 * if the cart is empty, causing transaction rollback and preventing notification logging.
 */
@Stateless
public class OrderService {

    @Inject
    private NotificationService notificationService;

    @Inject
    private CartService cartService;

    @Transactional
    public void createOrder(String name, String email, String address) {
        // Chapter 3 Activity 3: Guard against an empty cart
        if (cartService == null || cartService.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot create order: the cart is empty.");
        }

        // Order persistence arrives in Chapter 4 — for now, this represents
        // the transactional boundary the real save will live inside.
        if (notificationService != null) {
            notificationService.logOrderConfirmation(name);
        }
        cartService.clear();
    }
}
