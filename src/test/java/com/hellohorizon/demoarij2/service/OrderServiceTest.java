package com.hellohorizon.demoarij2.service;

import com.hellohorizon.demoarij2.model.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;
    private CartService cartService;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() throws Exception {
        orderService = new OrderService();
        cartService = new CartService();
        notificationService = new NotificationService();

        Field cartField = OrderService.class.getDeclaredField("cartService");
        cartField.setAccessible(true);
        cartField.set(orderService, cartService);

        Field notifField = OrderService.class.getDeclaredField("notificationService");
        notifField.setAccessible(true);
        notifField.set(orderService, notificationService);
    }

    @Test
    void testGuardAgainstEmptyCartThrowsException() {
        // Lab 3 Chapter 3 Activity 3: Cart is empty -> must throw exception, no notification
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            orderService.createOrder("Alice", "alice@example.com", "123 Elm St");
        });

        assertTrue(ex.getMessage().contains("cart is empty"));
        assertTrue(notificationService.getHistory().isEmpty(), "No notification should fire when cart is empty");
    }

    @Test
    void testSuccessfulOrderCompletion() {
        cartService.addItem(new ProductDTO(1L, "Laptop", "electronics", 1299.0));
        assertEquals(1, cartService.getItemCount());

        orderService.createOrder("Bob", "bob@example.com", "456 Oak St");

        // Cart should be cleared
        assertTrue(cartService.getItems().isEmpty());

        // Notification should be recorded in history
        assertEquals(1, notificationService.getHistory().size());
        assertEquals("Bob", notificationService.getHistory().get(0).getCustomerName());
    }
}
