package com.hellohorizon.demoarij2.service;

import jakarta.ejb.Singleton;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NotificationService - Lab 3 Step 5 & Chapter 3 Activity 2
 *
 * Singleton bean shared across the entire application lifecycle.
 * In Chapter 3 Activity 2, extended to store an in-memory history of the last 5
 * order confirmations (customer name + timestamp).
 * Because this bean is scoped as @Singleton, this in-memory list persists across
 * multiple checkouts performed by different users/sessions.
 */
@Singleton
public class NotificationService implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class OrderRecord implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String customerName;
        private final LocalDateTime timestamp;

        public OrderRecord(String customerName, LocalDateTime timestamp) {
            this.customerName = customerName;
            this.timestamp = timestamp;
        }

        public String getCustomerName() {
            return customerName;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "OrderRecord{" +
                    "customerName='" + customerName + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    private final List<OrderRecord> orderHistory = new ArrayList<>();

    public void logOrderConfirmation(String customerName) {
        System.out.println("[NOTIFY] Order confirmed for: " + customerName);
        synchronized (orderHistory) {
            orderHistory.add(new OrderRecord(customerName, LocalDateTime.now()));
            if (orderHistory.size() > 5) {
                orderHistory.remove(0); // retain only the last 5 confirmations
            }
        }
    }

    public List<OrderRecord> getHistory() {
        synchronized (orderHistory) {
            return Collections.unmodifiableList(new ArrayList<>(orderHistory));
        }
    }
}
