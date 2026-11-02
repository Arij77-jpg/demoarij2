package com.hellohorizon.demoarij2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    void testLogOrderConfirmationAndHistoryLimit() {
        assertTrue(notificationService.getHistory().isEmpty());

        for (int i = 1; i <= 7; i++) {
            notificationService.logOrderConfirmation("Customer " + i);
        }

        List<NotificationService.OrderRecord> history = notificationService.getHistory();
        // Lab 3 Chapter 3 Activity 2: must keep last 5 confirmations
        assertEquals(5, history.size());
        assertEquals("Customer 3", history.get(0).getCustomerName());
        assertEquals("Customer 7", history.get(4).getCustomerName());
        assertNotNull(history.get(4).getTimestamp());
    }
}
